package pk.home.voyager.domain;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

/**
 * Entity implementation class for Entity: Location
 * 
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "Location.findAll", query = "select a from Location a"),
		@NamedQuery(name = "Location.findAllOrderById", query = "select a from Location a order by a.id"),
		@NamedQuery(name = "Location.findByPrimaryKey", query = "select a from Location a where a.id = ?1"),

		@NamedQuery(name = "Location.findChildrens", query = "select a from Location a where a.parent = ?1"),
		@NamedQuery(name = "Location.findRootChildrens", query = "select a from Location a where a.parent = null"),

		@NamedQuery(name = "Location.findChildrensCount", query = "select count(a) from Location a where a.parent = ?1"),
		@NamedQuery(name = "Location.findRootChildrensCount", query = "select count(a) from Location a where a.parent = null") })
@Table(schema = "public", name = "tlocation")
public class Location implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8087387125112257371L;

	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "parent_id", referencedColumnName = "id")
	private Location parent;

	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private LType ltype;

	//
	// @OneToMany(mappedBy = "parent",cascade=CascadeType.ALL,
	// fetch=FetchType.EAGER)
	// private List<Location> children = new ArrayList<Location>();

	@NotNull
	@Column(nullable = false)
	private String title;
	
	@Length(max=3000)
	@Column(length=3000)
	private String description;

	public Location() {
		super();
	}

	public Location(Location parent, String title) {
		super();
		this.parent = parent;
		this.title = title;
	}

	public Location(String title) {
		super();
		this.title = title;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Location getParent() {
		return parent;
	}

	public void setParent(Location parent) {
		this.parent = parent;
	}

	// public List<Location> getChildren() {
	// return children;
	// }
	//
	// public void setChildren(List<Location> children) {
	// this.children = children;
	// }
	//
	// public void addChildren(Location c){
	// children.add(c);
	// }
	//

	/**
	 * @return the ltype
	 */
	public LType getLtype() {
		return ltype;
	}

	/**
	 * @param ltype
	 *            the ltype to set
	 */
	public void setLtype(LType ltype) {
		this.ltype = ltype;
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
		if (!(object instanceof Location)) {
			return false;
		}
		Location other = (Location) object;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "pk.home.voyager.domain.Location[ id=" + id + " ]";
	}

	
	
	
	public String getFullLocationPath() {
		String s = ltype.getKeyName() + ": " + title;

		Location tparent = parent;
		while (tparent != null) {
			s = tparent.getLtype().getKeyName() + ": " + tparent.getTitle() + " -> " + s;
			tparent = tparent.getParent();
		}

		return s;
	}

}
