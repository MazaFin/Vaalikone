/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persist;

import java.io.Serializable;
import java.util.logging.Logger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jonne
 */
@Entity
@Table(name = "KYSYMYKSET", schema="APP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Kysymykset.findAll", query = "SELECT k FROM Kysymykset k"),
    @NamedQuery(name = "Kysymykset.findByKysymysId", query = "SELECT k FROM Kysymykset k WHERE k.kysymysId = :kysymysId"),
    @NamedQuery(name = "Kysymykset.findByKysymys", query = "SELECT k FROM Kysymykset k WHERE k.kysymys = :kysymys")})
public class Kysymykset implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "KYSYMYS_ID")
    private Integer kysymysId;
    @Size(max = 100)
    @Column(name = "KYSYMYS")
    private String kysymys;

    /**
     *
     */
    public Kysymykset() {
    }

    /**
     *
     * @param kysymysId
     */
    public Kysymykset(Integer kysymysId) {
        this.kysymysId = kysymysId;
    }

    /**
     *
     * @return
     */
    public Integer getKysymysId() {
        return kysymysId;
    }

    /**
     *
     * @param kysymysId
     */
    public void setKysymysId(Integer kysymysId) {
        this.kysymysId = kysymysId;
    }

    /**
     *
     * @return
     */
    public String getKysymys() {
        return kysymys;
    }

    /**
     *
     * @param kysymys
     */
    public void setKysymys(String kysymys) {
        this.kysymys = kysymys;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kysymysId != null ? kysymysId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Kysymykset)) {
            return false;
        }
        Kysymykset other = (Kysymykset) object;
        return !((this.kysymysId == null && other.kysymysId != null) || (this.kysymysId != null && !this.kysymysId.equals(other.kysymysId)));
    }

    @Override
    public String toString() {
        return "persist.Kysymykset[ kysymysId=" + kysymysId + " ]";
    }
    private static final Logger LOG = Logger.getLogger(Kysymykset.class.getName());
    
}
