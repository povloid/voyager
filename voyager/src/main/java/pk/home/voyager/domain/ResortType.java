
package pk.home.voyager.domain;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

/**
 * Entity implementation class for Entity: ResortType
 * The resort type
 *
 */
@Entity
@Table(schema = "public", name = "ResortType")
@NamedQueries({
	@NamedQuery(name = "ResortType.findAll", query = "select a from ResortType a order by a.id"),
	@NamedQuery(name = "ResortType.findByPrimaryKey", query = "select a from ResortType a where a.id = ?1")})
public class ResortType implements Serializable {

	   
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
    @Column(unique=true, nullable = false)
	private String keyName;
	
	
//	@ManyToMany(mappedBy="resortTypes")
//	private List<Hotel> hotels = new ArrayList<Hotel>();
//	
//	
//
//	
//	public void setHotels(List<Hotel> hotels) {
//		this.hotels = hotels;
//	}
//	public List<Hotel> getHotels() {
//		return hotels;
//	}

	@Length(max=1000)
	@Column(length=1000)
	private String description;
	

	public ResortType() {
		super();
	}   
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}   
	public String getKeyName() {
		return this.keyName;
	}

	public void setKeyName(String keyName) {
		System.out.println(keyName);
		this.keyName = keyName;
	}   
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// not set
		if (!(object instanceof ResortType)) {
			return false;
		}
		ResortType other = (ResortType) object;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "pk.home.voyager.domain.ResortType[ id=" + id + " ]";
	}
   
}
