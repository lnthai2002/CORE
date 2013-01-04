/*
 * FinancialRecord.java
 *
 * Created on February 11, 2007, 10:30 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Financial;

/**
 *
 * @author nhut_le
 */
public class TransactionBean
{
    private int id;
    private String date;
    private int amount; //+ and -
    private String currency; //CND,VND,USD...
    private String note;
//------------------------------------------    
    /** Creates a new instance of TransactionBean */
    public TransactionBean(int id)
    {
        this.id = id;
        this.date = "";
        this.amount = 0;
        this.currency = "";
        this.note = "";
    }
//------------------------------------------    
    public TransactionBean()
    {
        this(0);
    }
//------------------------------------------
    public int getID()
    {
        return this.id;
    }
//------------------------------------------
    public String getDate()
    {
        return this.date;
    }
//------------------------------------------
    public void setDate(String date)
    {
        this.date = date;
    }
//------------------------------------------
    public int getAmount()
    {
        return this.amount;
    }
//------------------------------------------
    public void setAmount(int amount)
    {
        this.amount = amount;
    }
//------------------------------------------
    public String getCurrency()
    {
        return this.currency;
    }
//------------------------------------------
    public void setCurrency(String currency)
    {
        this.currency = currency;
    }
//------------------------------------------
    public String getNote()
    {
        return this.note;
    }
//------------------------------------------
    public void setNote(String note)
    {
        this.note = note;
    }
}
