package orama.ufv.br.operacoesemandamento.model;

/**
 * Created by usuario on 22/08/2017.
 */

public class WithdrawalCc {
    private SubAccounts subAccounts;
    private String withdrawalDate;
    private float value;
    private String status;
    private String bank;
    private String agency;
    private Account account;
    private String tipo;
    private boolean jointAccount;
    private String requestDate;
    private boolean isCancellable;

    public WithdrawalCc(SubAccounts subAccounts, String withdrawalDate, float value, String status, String bank, String agency, Account account, String tipo, boolean jointAccount, String requestDate, boolean isCancellable) {
        this.subAccounts = subAccounts;
        this.withdrawalDate = withdrawalDate;
        this.value = value;
        this.status = status;
        this.bank = bank;
        this.agency = agency;
        this.account = account;
        this.tipo = tipo;
        this.jointAccount = jointAccount;
        this.requestDate = requestDate;
        this.isCancellable = isCancellable;
    }

    public SubAccounts getSubAccounts() {
        return subAccounts;
    }

    public void setSubAccounts(SubAccounts subAccounts) {
        this.subAccounts = subAccounts;
    }

    public String getWithdrawalDate() {
        return withdrawalDate;
    }

    public void setWithdrawalDate(String withdrawalDate) {
        this.withdrawalDate = withdrawalDate;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String statur) {
        this.status = statur;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isJointAccount() {
        return jointAccount;
    }

    public void setJointAccount(boolean jointAccount) {
        this.jointAccount = jointAccount;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public boolean isCancellable() {
        return isCancellable;
    }

    public void setCancellable(boolean cancellable) {
        isCancellable = cancellable;
    }
}
