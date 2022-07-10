
public class Account {
    String customer_id;
    String acc_num;
    String acc_type;
    String currency;
    String balance;

    public Account(String customer_id, String acc_num, String acc_type, String currency, String balance) {
        this.customer_id = customer_id;
        this.acc_num = acc_num;
        this.acc_type = acc_type;
        this.currency = currency;
        this.balance = balance;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getAcc_num() {
        return acc_num;
    }

    public void setAcc_num(String acc_num) {
        this.acc_num = acc_num;
    }

    public String getAcc_type() {
        return acc_type;
    }

    public void setAcc_type(String acc_type) {
        this.acc_type = acc_type;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }


    @Override
    public String toString() {
        return customer_id + ',' +
               acc_num + ',' +
               acc_type + ',' +
               currency + ',' +
               balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        // returns false if any attributes are not equal
        if ((!customer_id.equals(account.customer_id)) || (!acc_num.equals(account.acc_num)) || (!acc_type.equals(account.acc_type)) || (!currency.equals(account.currency)) || (!balance.equals(account.balance))) {
            return false;
        } else {
            return true;
        }

    }

}
