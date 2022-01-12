package com.example.utils;

import com.jcraft.jsch.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import lombok.Builder;
import lombok.Data;
import org.springframework.util.Assert;

import java.io.*;
import java.util.Properties;

@Slf4j
public class FTPUtils {

    private static final ThreadLocal<Session> sessionThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<Channel> channelThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<ChannelSftp> channelSftpThreadLocal = new ThreadLocal<>();

    /**
     * 文件上传
     * @param file 文件
     * @param dir 目标文件夹
     * @param ftpInfo ftp信息
     */
    public static void uploadFile(File file, String dir, FtpInfo ftpInfo) {
        //非空校验
        uploadAssert(ftpInfo);

        //获取sftp连接
        log.info("sftp文件上传开始 , 开启sftp连接 ... ");
        ChannelSftp channelSftp = getChannel(
                ftpInfo.getHosts(),
                ftpInfo.getFtpUserName(),
                ftpInfo.getPassword(),
                ftpInfo.getPort());

        channelSftpThreadLocal.set(channelSftp);
        log.info("切换到目标: {} ",ftpInfo.getPath()+"/"+dir);
        //切换到目标目录
        cdPath(ftpInfo.getPath());
        cdTarget(dir);

        //文件上传
        try(FileInputStream fileInputStream = new FileInputStream(file)) {
            log.info("开始上传文件，文件名为:{}",ftpInfo.getFileName());
            channelSftp.put(fileInputStream,ftpInfo.getFileName());
            log.info("sftp 文件上传成功 ！ ");
        } catch (IOException | SftpException e) {
            log.error("sftp文件上传失败 ! ",e);
            throw new RuntimeException(e);

        }finally {
            log.info("文件上传关闭sftp连接 ... ");
            closeChannel();
        }
    }

    /**
     * 文件上传非空校验
     * @param ftpInfo ftp配置信息
     */
    private static void uploadAssert(FtpInfo ftpInfo) {
        Assert.notNull(ftpInfo.getHosts(),"hosts short ！");
        Assert.notNull(ftpInfo.getFtpUserName(),"ftpUserName short ！");
        Assert.notNull(ftpInfo.getPassword(),"password short ！");
        Assert.notNull(ftpInfo.getPort(),"port short ！");
        Assert.notNull(ftpInfo.getFileName(),"fileName short ！");
    }

    public static InputStream download(String path, FtpInfo ftpInfo){
        log.info("sftp下载开始 , 开启sftp连接 ... ");
        ChannelSftp channelSftp = getChannel(
                ftpInfo.getHosts(),
                ftpInfo.getFtpUserName(),
                ftpInfo.getPassword(),
                ftpInfo.getPort());

        //拼接文件路径 path+filename
        String fileName = ftpInfo.getFileName();
        if (StringUtils.isNotEmpty(path))
            fileName = path + "/" + fileName;

        log.info("下载文件的绝对路径：{}",fileName);
        try {
            //返回文件流
            return channelSftp.get(fileName);
        } catch (SftpException e) {
            log.info("获取sftp文件异常 ！",e);
            throw new RuntimeException(e);
        }finally {
            log.info("文件下载关闭sftp连接 ... ");
            closeChannel();
        }
    }

    /**
     * 切换目标文件夹
     * @param dir 目标文件夹
     */
    private static void cdTarget(String dir){
        if (StringUtils.isNotEmpty(dir)) {
            ChannelSftp channelSftp = channelSftpThreadLocal.get();
            try {
                channelSftp.cd(dir);
            } catch (SftpException e) {
                log.warn("目标文件夹 {} 不存在，创建文件夹！",dir);
                try {
                    channelSftp.mkdir(dir);
                } catch (SftpException ex) {
                    log.error("sftp创建目标文件夹：{} 异常为：",dir,e);
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * 切换配置目录
     * @param path 配置路径
     */
    private static void cdPath(String path){
        if (StringUtils.isNotEmpty(path)) {
            ChannelSftp channelSftp = channelSftpThreadLocal.get();
            try {
                channelSftp.cd(path);
            } catch (SftpException e) {
                log.error("sftp切换配置path: {} 异常为： ",path,e);
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * jsch获取ssh 通道
     * @param hosts 地址
     * @param userName ftp用户名
     * @param password ftp密码
     * @param port 端口号
     * @return ChannelSftp
     */
    private static ChannelSftp getChannel(String hosts,String userName,String password,Integer port) {
        JSch jsch = new JSch();

        try {
            log.info("当前sftp连接地址："+hosts+":"+port+"/"+userName+"/"+password);
            Session session = jsch.getSession(userName, hosts, port);
            session.setPassword(password);
            Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setTimeout(30000);
            session.setConfig(config);
            session.connect();
            log.info("建立 sftp session 连接 ...");
            Channel channel = session.openChannel("sftp");
            log.info("建立 sftp channel 连接 ...");
            channel.connect();

            sessionThreadLocal.set(session);
            channelThreadLocal.set(channel);

            return (ChannelSftp) channel;

        } catch (JSchException e) {
            log.error("获取jsch连接异常", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 关闭ssh通道
     */
    private static void closeChannel() {
        if (channelThreadLocal != null) {
            Channel channel = channelThreadLocal.get();
            log.info("断开 channel 连接 ... ");
            channel.disconnect();
        }
        if (sessionThreadLocal != null) {
            Session session = sessionThreadLocal.get();
            log.info("断开 session 连接 ... ");
            session.disconnect();
        }
    }

    /**
     * 内部类 维护ftp信息
     */
    @Data
    @Builder
    public static class FtpInfo{
        private String hosts;
        private String ftpUserName;
        private String password;
        private Integer port;
        private String fileName;
        private String path;
    }

}
