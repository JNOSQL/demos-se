package org.jnosql.artemis.demo.se;

import jakarta.nosql.mapping.Column;
import jakarta.nosql.mapping.Entity;
import jakarta.nosql.mapping.Id;
import org.bson.types.Binary;

import java.util.Date;
import java.util.Set;

@Entity
public class Vendor {
    @Id
    private String name;

    @Column
    private Set<String> prefixes;

    @Column
    private Binary logo;

    @Column
    private String logoMimeType;

    @Column
    private Date logoLastModified;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getPrefixes() {
        return prefixes;
    }

    public void setPrefixes(Set<String> prefixes) {
        this.prefixes = prefixes;
    }

    public Binary getLogo() {
        return logo;
    }

    public void setLogo(Binary logo) {
        this.logo = logo;
    }

    public String getLogoMimeType() {
        return logoMimeType;
    }

    public void setLogoMimeType(String logoMimeType) {
        this.logoMimeType = logoMimeType;
    }

    public Date getLogoLastModified() {
        return logoLastModified;
    }

    public void setLogoLastModified(Date logoLastModified) {
        this.logoLastModified = logoLastModified;
    }

}
