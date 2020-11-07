package orama.ufv.br.operacoesemandamento.presenter.Util;

import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import orama.ufv.br.operacoesemandamento.model.Account;
import orama.ufv.br.operacoesemandamento.model.ApplicationFunds;
import orama.ufv.br.operacoesemandamento.model.BuyFixedIncome;
import orama.ufv.br.operacoesemandamento.model.Redemption;
import orama.ufv.br.operacoesemandamento.model.SubAccounts;
import orama.ufv.br.operacoesemandamento.model.WithdrawalCc;

/**
 * Created by usuario on 22/08/2017.
 */

public class OverloadPresenters {
    private static OverloadPresenters uniqueInstance = new OverloadPresenters();

    private Account account = new Account();
    private List<SubAccounts> subAccountses = new ArrayList<SubAccounts>();
    private List<BuyFixedIncome> buyFixedIncomes = new ArrayList<BuyFixedIncome>();
    private List<WithdrawalCc> withdrawalCcs = new ArrayList<WithdrawalCc>();
    private List<ApplicationFunds> applicationFundses = new ArrayList<ApplicationFunds>();
    private List<Redemption> redemptions = new ArrayList<Redemption>();


    public static OverloadPresenters getUniqueInstance() {
        return uniqueInstance;
    }

    public static void setUniqueInstance(OverloadPresenters uniqueInstance) {
        OverloadPresenters.uniqueInstance = uniqueInstance;
    }

    public List<ApplicationFunds> getApplicationFundses() {
        return applicationFundses;
    }

    public void setApplicationFundses(List<ApplicationFunds> applicationFundses) {
        this.applicationFundses = applicationFundses;
    }

    public List<Redemption> getRedemptions() {
        return redemptions;
    }

    public void setRedemptions(List<Redemption> redemptions) {
        this.redemptions = redemptions;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<SubAccounts> getSubAccountses() {
        return subAccountses;
    }

    public void setSubAccountses(List<SubAccounts> subAccountses) {
        this.subAccountses = subAccountses;
    }

    public List<BuyFixedIncome> getBuyFixedIncomes() {
        return buyFixedIncomes;
    }

    public void setBuyFixedIncomes(List<BuyFixedIncome> buyFixedIncomes) {
        this.buyFixedIncomes = buyFixedIncomes;
    }


    public List<WithdrawalCc> getWithdrawalCcs() {
        return withdrawalCcs;
    }

    public void setWithdrawalCcs(List<WithdrawalCc> withdrawalCcs) {
        this.withdrawalCcs = withdrawalCcs;
    }

    private OverloadPresenters(){
        OverloadSubAccountsPresenter();
        OverloadAccountPresenter();
        OverloadBuyFixedIncomePresenter();
        OverloadWithdrawalCcPresenter();
        OverloadApplicationFunds();
        OverloadRedemption();
    }

    public static OverloadPresenters getInstance() {
        return uniqueInstance;
    }

    public Account OverloadAccountPresenter(){
        account.setId(1010);
        account.setPassword(Arrays.asList("a", "c", "2017"));
        account.setUser("igor.cardoso");
        return account;
    }
    public List<SubAccounts> OverloadSubAccountsPresenter(){
        subAccountses.add(new SubAccounts("1010-1", account));
        subAccountses.add(new SubAccounts("1010-2", account));
        subAccountses.add(new SubAccounts("1010-3", account));
        account.setSubAccountses(subAccountses);
        return subAccountses;
    }

    public List<BuyFixedIncome> OverloadBuyFixedIncomePresenter(){

        buyFixedIncomes.add(new BuyFixedIncome(subAccountses.get(0), "10/05/2017", "Renda alta", 1500.00f, "20/07/2017", "Efetivado", true));
        subAccountses.get(0).setBuyFixedIncomes(Arrays.asList(buyFixedIncomes.get(0)));

        buyFixedIncomes.add(new BuyFixedIncome(subAccountses.get(2), "12/03/2016", "Renda baixa", 500.00f, "24/01/2017", "Efetivado", false));
        buyFixedIncomes.add(new BuyFixedIncome(subAccountses.get(2), "12/07/2016", "Renda media", 800.00f, "10/02/2017", "Efetivado", true));
        subAccountses.get(2).setBuyFixedIncomes(Arrays.asList(buyFixedIncomes.get(1), buyFixedIncomes.get(2)));
        return buyFixedIncomes;
    }

    public List<WithdrawalCc> OverloadWithdrawalCcPresenter(){

        withdrawalCcs.add(new WithdrawalCc(subAccountses.get(0), "10/02/2000", 1500.0f, "Ativo", "BB", "42896-2", account, "1", true, "01/11/2016", true));
        withdrawalCcs.add(new WithdrawalCc(subAccountses.get(1), "13/10/2013", 100.0f, "Desativado", "Caixa", "9994-1", account, "2", true, "01/12/2013", false));
        withdrawalCcs.add(new WithdrawalCc(subAccountses.get(1), "10/06/2010", 2000.0f, "Ativo", "Santander", "2020-9", account, "1", false, "07/09/2011", false));
        withdrawalCcs.add(new WithdrawalCc(subAccountses.get(2), "09/09/2009", 2500.0f, "Ativo", "Bradesco", "8767-1", account, "3", true, "04/09/2009", true));

        subAccountses.get(0).setWithdrawalCcs(Arrays.asList(withdrawalCcs.get(0)));
        subAccountses.get(1).setWithdrawalCcs(Arrays.asList(withdrawalCcs.get(1), withdrawalCcs.get(2)));
        subAccountses.get(2).setWithdrawalCcs(Arrays.asList(withdrawalCcs.get(3)));

        Log.i("Rastreio", "5: " + withdrawalCcs.size());
        return withdrawalCcs;
    }
    public List<ApplicationFunds> OverloadApplicationFunds(){

        applicationFundses.add(new ApplicationFunds(subAccountses.get(0), "10/05/2001", 1000.0f, "Ativada", "09/09/2009 12:13:14", "07/05/2017 19:20:21", true, "fundo 1" ));

        applicationFundses.add(new ApplicationFunds(subAccountses.get(0), "03/01/2004", 100.0f, "Ativada", "07/11/2008 21:22:23", "19/05/2007 01:02:03", false, "fundo 2" ));

        applicationFundses.add(new ApplicationFunds(subAccountses.get(1), "17/03/2003", 1900.0f, "Desativada", "02/02/2017", "07/05/2014", false, "fundo 3" ));

        applicationFundses.add(new ApplicationFunds(subAccountses.get(2), "10/05/2001", 8000.0f, "Ativada", "08/04/2002", "30/12/2017", true, "fundo 4" ));

        subAccountses.get(0).setApplicationFundses(Arrays.asList(applicationFundses.get(0), applicationFundses.get(1)));
        subAccountses.get(1).setApplicationFundses(Arrays.asList(applicationFundses.get(2)));
        subAccountses.get(2).setApplicationFundses(Arrays.asList(applicationFundses.get(3)));
        return applicationFundses;
    }
    public  List<Redemption> OverloadRedemption(){
        redemptions.add(new Redemption(subAccountses.get(2), "10/05/2001", 1000.0f, "Ativada", "09/09/2009 12:34:56", "11/09/2009 03:45:01", "Não", "30/12/2017",true));
        subAccountses.get(2).setRedemptions(Arrays.asList(redemptions.get(0)));
        redemptions.add(new Redemption(subAccountses.get(1), "10/05/2001", 1000.0f, "Ativada", "09/09/2009 23:12:00", "23/11/2009 11:34:12", "Não", "30/12/2017",false));
        subAccountses.get(1).setRedemptions(Arrays.asList(redemptions.get(1)));
        return redemptions;

    }

}
