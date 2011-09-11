package pk.home.voyager.domain;

import java.io.Serializable;
import java.lang.Long;
import java.lang.String;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Entity implementation class for Entity: LType
 * This is a type of location	
 *
 */
@Entity
@Table(schema = "public", name = "ltype")
@NamedQueries({
	@NamedQuery(name = "LType.findAll", query = "select a from Location a order by a.id"),
	@NamedQuery(name = "LType.findByPrimaryKey", query = "select a from Location a where a.id = ?1")})
public class LType implements Serializable {

	   
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5821420668615190298L;

	@Column(nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
    @Column(unique=true, nullable = false)
	private String keyName;
	
	
	private String description;
	

	public LType() {
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
		if (!(object instanceof LType)) {
			return false;
		}
		LType other = (LType) object;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "pk.home.voyager.domain.LType[ id=" + id + " ]";
	}
   
}
