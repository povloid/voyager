
package pk.home.voyager.domain;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

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
	
	@Length(max=3000)
	@Column(length=3000)
	private String description;
	private double rating;
	
		
	public List<ResortType> getResortTypes() {
		return resortTypes;
	}
	public void setResortTypes(List<ResortType> resortTypes) {
		this.resortTypes = resortTypes;
	}

	

	@ElementCollection(targetClass=Image.class)
	private Set<Image> images;
	
	@ElementCollection(targetClass=GMapMarker.class)
	private Set<GMapMarker> gMapMarkers;

	private int beachline;
	
	

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
		System.out.println("Rating - " + rating);
		this.rating = rating;
	}
	
	public Set<Image> getImages() {
		return images;
	}
	public void setImages(Set<Image> images) {
		this.images = images;
	}
	
	public Set<GMapMarker> getgMapMarkers() {
		return gMapMarkers;
	}
	public void setgMapMarkers(Set<GMapMarker> gMapMarkers) {
		this.gMapMarkers = gMapMarkers;
	}
	
	
	
	
	public int getBeachline() {
		return beachline;
	}
	public void setBeachline(int beachline) {
		this.beachline = beachline;
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
