
package pk.home.voyager.domain;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.sun.istack.Nullable;

/**
 * Entity implementation class for Entity: Hotel
 * Hotel
 *
 */
@Entity
@Table(schema = "public", name = "Hotel")
@NamedQueries({
	@NamedQuery(name = "Hotel.findAll", query = "select a from Hotel a order by a.id"),
	@NamedQuery(name = "Hotel.findByPrimaryKey", query = "select a from Hotel a where a.id = ?1")})
public class Hotel implements Serializable {

	   
	
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
	

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="hotel_resort_types",
	joinColumns=@JoinColumn(name="hotel_id"),
	inverseJoinColumns=@JoinColumn(name="resort_type_id"))
	private List<ResortType> resortTypes = new ArrayList<ResortType>();
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Location location;
	
	
	private String description;
	private double rating;
	
		
	public List<ResortType> getResortTypes() {
		return resortTypes;
	}
	public void setResortTypes(List<ResortType> resortTypes) {
		this.resortTypes = resortTypes;
	}

	
	

	public Hotel() {
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
	
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
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
		if (!(object instanceof Hotel)) {
			return false;
		}
		Hotel other = (Hotel) object;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "pk.home.voyager.domain.Hotel[ id=" + id + " ]";
	}
	
	
   
}
