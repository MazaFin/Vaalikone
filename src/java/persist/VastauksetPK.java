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
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Jonne
 */
@Embeddable
public class VastauksetPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "EHDOKAS_ID")
    private int ehdokasId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "KYSYMYS_ID")
    private int kysymysId;

    /**
     *
     */
    public VastauksetPK() {
    }

    /**
     *
     * @param ehdokasId
     * @param kysymysId
     */
    public VastauksetPK(int ehdokasId, int kysymysId) {
        this.ehdokasId = ehdokasId;
        this.kysymysId = kysymysId;
    }

    /**
     *
     * @return
     */
    public int getEhdokasId() {
        return ehdokasId;
    }

    /**
     *
     * @param ehdokasId
     */
    public void setEhdokasId(int ehdokasId) {
        this.ehdokasId = ehdokasId;
    }

    /**
     *
     * @return
     */
    public int getKysymysId() {
        return kysymysId;
    }

    /**
     *
     * @param kysymysId
     */
    public void setKysymysId(int kysymysId) {
        this.kysymysId = kysymysId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += ehdokasId;
        hash += kysymysId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VastauksetPK)) {
            return false;
        }
        VastauksetPK other = (VastauksetPK) object;
        if (this.ehdokasId != other.ehdokasId) {
            return false;
        }
        return this.kysymysId == other.kysymysId;
    }

    @Override
    public String toString() {
        return "persist.VastauksetPK[ ehdokasId=" + ehdokasId + ", kysymysId=" + kysymysId + " ]";
    }
    private static final Logger LOG = Logger.getLogger(VastauksetPK.class.getName());
    
}
