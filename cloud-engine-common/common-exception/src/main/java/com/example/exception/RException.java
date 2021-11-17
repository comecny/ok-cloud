package com.example.exception;

public class RException extends Exception implements Messageable{
    private static final long serialVersionUID = 7158924729975437038L;
    private MessageSupport msgSupport = new MessageSupport();
    private static final String DEFAULT_EX_KEY = ".undefined";
    private Object[] args;

    public RException() {
        super("");
        this.msgSupport.setMessageKey(".undefined");
    }

    public RException(String args) {
        super(args);
        if (args != null && args.trim().length() != 0) {
            this.msgSupport.setMessageKey(args);
        } else {
            this.msgSupport.setMessageKey(".undefined");
        }
    }

    public RException(String arg, Object[] args) {
        super(arg);
        this.args = args;
        if (arg != null && arg.trim().length() != 0) {
            this.msgSupport.setMessageKey(arg);
        } else {
            this.msgSupport.setMessageKey(".undefined");
        }

        this.msgSupport.setArgs(args);
    }

    public RException(String arg, Throwable throwable) {
        super(arg, throwable);
        if (arg != null && arg.trim().length() != 0) {
            this.msgSupport.setMessageKey(arg);
        } else {
            this.msgSupport.setMessageKey(".undefined");
        }

    }

    public RException(String arg, Throwable throwable, Object[] args) {
        super(arg, throwable);
        this.args = args;
        if (arg != null && arg.trim().length() != 0) {
            this.msgSupport.setMessageKey(arg);
        } else {
            this.msgSupport.setMessageKey(".undefined");
        }

        this.msgSupport.setArgs(args);
    }

    public RException(Throwable throwable) {
        super("", throwable);
        this.msgSupport.setMessageKey(".undefined");
    }

    public boolean hasDefaultMessage() {
        return this.msgSupport.hasDefaultMessage();
    }

    public void setDefaultMessage(String message) {
        this.msgSupport.setDefaultMessage(message);
    }

    public String getDefaultMessage() {
        String result = this.msgSupport.getDefaultMessage();
        if (result != null) {
            return result;
        } else {
            StringBuffer sbf = (new StringBuffer(this.getClass().getName())).append(" MessageCode: ").append(this.getMessageKey());
            if (this.msgSupport.getArgs() != null) {
                sbf.append(" Args: ");
                Object[] args = this.msgSupport.getArgs();

                for(int i = 0; i < args.length; ++i) {
                    sbf.append(args[i]).append(" ");
                }
            }

            if (this.getCause() != null) {
                sbf.append(" nested exception is: ");
                sbf.append(this.getCause());
            }

            return sbf.toString();
        }
    }

    public String toString() {
        StringBuffer sbf = new StringBuffer(super.toString());
        if (this.msgSupport.getArgs() != null) {
            sbf.append(" Args:");
            Object[] args = this.msgSupport.getArgs();

            for(int i = 0; i < args.length; ++i) {
                sbf.append(args[i]).append(" ");
            }
        }

        return sbf.toString();
    }

    public String getMessageKey() {
        return this.msgSupport.getMessageKey();
    }

    public Object[] getArgs() {
        return this.msgSupport.getArgs();
    }

    public void setArgs(Object[] args) {
        this.args = args;
        this.msgSupport.setArgs(args);
    }

    public String getMessage() {
        StringBuffer sbf;
        int i;
        if (this.getCause() == null) {
            if (this.args == null) {
                return super.getMessage();
            } else {
                sbf = new StringBuffer(super.getMessage());
                sbf.append(" Args: ");

                for(i = 0; i < this.args.length; ++i) {
                    sbf.append(this.args[i]).append(" ");
                }

                return sbf.toString();
            }
        } else if (this.args == null) {
            return super.getMessage() + "; nested exception is " + this.getCause().getClass().getName() + ": " + this.getCause().getMessage();
        } else {
            sbf = new StringBuffer(super.getMessage());
            sbf.append(" Args: ");

            for(i = 0; i < this.args.length; ++i) {
                sbf.append(this.args[i]).append(" ");
            }

            sbf.append("; nested exception is ").append(this.getCause().getClass().getName()).append(": ").append(this.getCause().getMessage());
            return sbf.toString();
        }
    }

}
