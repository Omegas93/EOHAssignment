package za.co.digitalplatoon.invoiceservice.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * Entity implementation class for Entity: LineItem
 *
 */
@Entity
@Table(name = "line_item", schema = "eoh")
@JsonIdentityInfo(
		generator=ObjectIdGenerators.PropertyGenerator.class,
		property="id")
public class LineItem implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long quantity;
	private String description;
	private BigDecimal unitPrice;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "invoice_id")
	@JsonIgnore
	private Invoice invoice;

	public LineItem() {
		super();
	}   
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}   

	public Long getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}   
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}  
	
	public BigDecimal getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	public Invoice getInvoice() {
		return invoice;
	}
	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
	
	@SuppressWarnings("deprecation")
	@Transient
	public BigDecimal getLineItemTotal() {
		
		BigDecimal total =  BigDecimal.ZERO;
		BigDecimal quantity = new BigDecimal(this.quantity.longValue());
		total = quantity.multiply(unitPrice);
		
		return total.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) {
			
			return true;
		}
		
		if(!(o instanceof LineItem)) {
			
			return false;
		}
		
		return id != null && id.equals(((LineItem) o).id);
	}
	
	@Override
	public int hashCode() {
		return 31;
	}
}
