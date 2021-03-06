package com.itaki.radyodinlenir.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@Table(name = "application_config")
@NamedQueries({ @NamedQuery(name = ApplicationConfig.GET_APPLICATION_CONFIG_BY_NAME, query = "select c from ApplicationConfig c where c.name=:name"),
		@NamedQuery(name = ApplicationConfig.GET_ALL_APPLICATON_CONFIGS, query = "select c from ApplicationConfig c order by c.id") })
public class ApplicationConfig {

	public final static String GET_APPLICATION_CONFIG_BY_NAME = "ApplicationConfig.getApplicationConfigByName";
	public final static String GET_ALL_APPLICATON_CONFIGS = "ApllicationConfig.getAllApplicationConfigs";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "description",length = 5000)
	private String description;

	@Column(name = "short_code")
	private String shortCode;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getShortCode() {
		return shortCode;
	}

	public void setShortCode(String shortCode) {
		this.shortCode = shortCode;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(21, 23).append(id).append(name).append(description).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}
		ApplicationConfig other = (ApplicationConfig) obj;
		return new EqualsBuilder().append(id, other.getId()).append(name, other.getName()).append(description, other.getDescription()).isEquals();
	}

}
