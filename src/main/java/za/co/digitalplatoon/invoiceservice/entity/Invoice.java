package za.co.digitalplatoon.invoiceservice.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * Entity implementation class for Entity: Invoice
 *
 */
@Entity
@Table(name= "invoice", schema = "eoh")
@JsonIdentityInfo(
		generator=ObjectIdGenerators.PropertyGenerator.class,
		property="id")
public class Invoice implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	private String client;
	private Long vatRate;
	private Date invoiceDate;
	@OneToMany(
			mappedBy = "invoice",
			cascade = CascadeType.ALL,
			orphanRemoval = true
			)
	private List<LineItem> lineItems = new ArrayList<>();
	
	public Invoice() {
		super();
	}   
	public Long getId() {
		return this.Id;
	}

	public void setId(Long Id) {
		this.Id = Id;
	}   
	public String getClient() {
		return this.client;
	}

	public void setClient(String client) {
		this.client = client;
	}   
	public Long getVatRate() {
		return this.vatRate;
	}

	public void setVatRate(Long vatRate) {
		this.vatRate = vatRate;
	}   
	public Date getInvoiceDate() {
		return this.invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public List<LineItem> getLineItems() {
		return lineItems;
	}
	public void setLineItems(List<LineItem> lineItems) {
		this.lineItems = lineItems;
	}
	
	public void addLineItem(LineItem lineItem) {
		lineItems.add(lineItem);
		lineItem.setInvoice(this);
	}
	
	public void removeLineItem(LineItem lineItem) {
		lineItems.remove(lineItem);
		lineItem.setInvoice(null);
	}
   
	@SuppressWarnings("deprecation")
	@Transient
	public BigDecimal getSubTotal() {
		
		BigDecimal subTotal = BigDecimal.ZERO;
		
		for (LineItem lineItem: lineItems) {
			
			subTotal = subTotal.add(lineItem.getLineItemTotal());
		}
		
		return subTotal.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
	@SuppressWarnings("deprecation")
	@Transient
	public BigDecimal getVat() {
		
		BigDecimal vat = BigDecimal.ZERO;
		BigDecimal vatPercent = new BigDecimal((double) (vatRate.doubleValue() / 100));
		vat = getSubTotal().multiply(vatPercent); 
		
		return vat.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
	@SuppressWarnings("deprecation")
	@Transient
	public BigDecimal getTotal() {
		
		BigDecimal total = BigDecimal.ZERO;
		
		total = getSubTotal().add(getVat());
		
		return total.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
}
