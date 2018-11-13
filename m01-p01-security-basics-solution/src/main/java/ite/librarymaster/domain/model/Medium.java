package ite.librarymaster.domain.model;

import javax.persistence.*;

/**
 * 
 * This class models general Medium, which can be stored in Library.
 * 
 * @author macalak@itexperts.sk
 *
 */
@MappedSuperclass
public abstract class Medium {
	
	@Id
	@GeneratedValue
	private Long id;
	private String cid;
	private String title;
	@Enumerated(EnumType.STRING)
	private MediumAvailability availability;
	
	public Medium() {
		super();
	}
	
	public Medium(Long id, String catId, String title, MediumAvailability availability) {
		super();
		this.id = id;
		this.cid = catId;
		this.title = title;
		this.availability = availability;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String catId) {
		this.cid = catId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public MediumAvailability getAvailability() {
		return availability;
	}

	public void setAvailability(MediumAvailability availability) {
		this.availability = availability;
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("Medium{");
		sb.append("id=").append(id);
		sb.append(", cid='").append(cid).append('\'');
		sb.append(", title='").append(title).append('\'');
		sb.append(", availability=").append(availability);
		sb.append('}');
		return sb.toString();
	}
}
