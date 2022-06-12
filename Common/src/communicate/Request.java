package communicate;

import util.ProductWrapper;

import java.io.Serializable;

/**
 * Class to communicate with server
 */
public class Request implements Serializable {
    private final String command;
    private final String[] args;
    private final ProductWrapper product;

    public Request(String command, String[] args, ProductWrapper product) {
        this.command = command;
        this.args = args;
        this.product = product;
    }

    public Request(String command, ProductWrapper pr) {
        this.command = command;
        this.product = pr;
        this.args = new String[0];
    }
    public Request(String command, String[] args) {
        this.command = command;
        this.args = args;
        this.product = new ProductWrapper();
    }

    public Request(String command) {
        this.command = command;
        this.args = new String[0];
        this.product = new ProductWrapper();
    }

    public String getCommand() {
        return command;
    }

    public String[] getArgs() {
        return args;
    }

    public ProductWrapper getProduct() {
        return product;
    }

}
