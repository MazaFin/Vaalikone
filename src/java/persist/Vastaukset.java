/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persist;

import java.io.Serializable;
import java.util.logging.Logger;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jonne
 */
@Entity
@Table(name = "VASTAUKSET", schema="APP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vastaukset.findAll", query = "SELECT v FROM Vastaukset v"),
    @NamedQuery(name = "Vastaukset.findByEhdokasId", query = "SELECT v FROM Vastaukset v WHERE v.vastauksetPK.ehdokasId = :ehdokasId"),
    @NamedQuery(name = "Vastaukset.findByKysymysId", query = "SELECT v FROM Vastaukset v WHERE v.vastauksetPK.kysymysId = :kysymysId"),
    @NamedQuery(name = "Vastaukset.findByVastaus", query = "SELECT v FROM Vastaukset v WHERE v.vastaus = :vastaus"),
    @NamedQuery(name = "Vastaukset.findByKommentti", query = "SELECT v FROM Vastaukset v WHERE v.kommentti = :kommentti")})
public class Vastaukset implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @EmbeddedId
    protected VastauksetPK vastauksetPK;
    @Column(name = "VASTAUS")
    private Integer vastaus;
    @Size(max = 50)
    @Column(name = "KOMMENTTI")
    private String kommentti;

    /**
     *
     */
    public Vastaukset() {
    }

    /**
     *
     * @param vastauksetPK
     */
    public Vastaukset(VastauksetPK vastauksetPK) {
        this.vastauksetPK = vastauksetPK;
    }

    /**
     *
     * @param ehdokasId
     * @param kysymysId
     */
    public Vastaukset(int ehdokasId, int kysymysId) {
        this.vastauksetPK = new VastauksetPK(ehdokasId, kysymysId);
    }

    /**
     *
     * @return
     */
    public VastauksetPK getVastauksetPK() {
        return vastauksetPK;
    }

    /**
     *
     * @param vastauksetPK
     */
    public void setVastauksetPK(VastauksetPK vastauksetPK) {
        this.vastauksetPK = vastauksetPK;
    }

    /**
     *
     * @return
     */
    public Integer getVastaus() {
        return vastaus;
    }

    /**
     *
     * @param vastaus
     */
    public void setVastaus(Integer vastaus) {
        this.vastaus = vastaus;
    }

    /**
     *
     * @return
     */
    public String getKommentti() {
        return kommentti;
    }

    /**
     *
     * @param kommentti
     */
    public void setKommentti(String kommentti) {
        this.kommentti = kommentti;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vastauksetPK != null ? vastauksetPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vastaukset)) {
            return false;
        }
        Vastaukset other = (Vastaukset) object;
        return !((this.vastauksetPK == null && other.vastauksetPK != null) || (this.vastauksetPK != null && !this.vastauksetPK.equals(other.vastauksetPK)));
    }

    @Override
    public String toString() {
        return "persist.Vastaukset[ vastauksetPK=" + vastauksetPK + " ]";
    }
    private static final Logger LOG = Logger.getLogger(Vastaukset.class.getName());
    
}
