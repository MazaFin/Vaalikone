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
@Table(name = "EHDOKKAAT", schema="APP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ehdokkaat.findAll", query = "SELECT e FROM Ehdokkaat e"),
    @NamedQuery(name = "Ehdokkaat.findByEhdokasId", query = "SELECT e FROM Ehdokkaat e WHERE e.ehdokasId = :ehdokasId"),
    @NamedQuery(name = "Ehdokkaat.findBySukunimi", query = "SELECT e FROM Ehdokkaat e WHERE e.sukunimi = :sukunimi"),
    @NamedQuery(name = "Ehdokkaat.findByEtunimi", query = "SELECT e FROM Ehdokkaat e WHERE e.etunimi = :etunimi"),
    @NamedQuery(name = "Ehdokkaat.findByPuolue", query = "SELECT e FROM Ehdokkaat e WHERE e.puolue = :puolue"),
    @NamedQuery(name = "Ehdokkaat.findByKotipaikkakunta", query = "SELECT e FROM Ehdokkaat e WHERE e.kotipaikkakunta = :kotipaikkakunta"),
    @NamedQuery(name = "Ehdokkaat.findByIk\u00e4", query = "SELECT e FROM Ehdokkaat e WHERE e.ik\u00e4 = :ik\u00e4"),
    @NamedQuery(name = "Ehdokkaat.findByMiksiEduskuntaan", query = "SELECT e FROM Ehdokkaat e WHERE e.miksiEduskuntaan = :miksiEduskuntaan"),
    @NamedQuery(name = "Ehdokkaat.findByMitaAsioitaHaluatEdistaa", query = "SELECT e FROM Ehdokkaat e WHERE e.mitaAsioitaHaluatEdistaa = :mitaAsioitaHaluatEdistaa"),
    @NamedQuery(name = "Ehdokkaat.findByAmmatti", query = "SELECT e FROM Ehdokkaat e WHERE e.ammatti = :ammatti")})
public class Ehdokkaat implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "EHDOKAS_ID")
    private Integer ehdokasId;
    @Size(max = 25)
    @Column(name = "SUKUNIMI")
    private String sukunimi;
    @Size(max = 25)
    @Column(name = "ETUNIMI")
    private String etunimi;
    @Size(max = 50)
    @Column(name = "PUOLUE")
    private String puolue;
    @Size(max = 25)
    @Column(name = "KOTIPAIKKAKUNTA")
    private String kotipaikkakunta;
    @Column(name = "IK\u00c4")
    private Integer ikä;
    @Size(max = 250)
    @Column(name = "MIKSI_EDUSKUNTAAN")
    private String miksiEduskuntaan;
    @Size(max = 2_000)
    @Column(name = "MITA_ASIOITA_HALUAT_EDISTAA")
    private String mitaAsioitaHaluatEdistaa;
    @Size(max = 50)
    @Column(name = "AMMATTI")
    private String ammatti;

    /**
     *
     */
    public Ehdokkaat() {
    }

    /**
     *
     * @param ehdokasId
     */
    public Ehdokkaat(Integer ehdokasId) {
        this.ehdokasId = ehdokasId;
    }

    /**
     *
     * @return ehdokkaan id-numero
     */
    public Integer getEhdokasId() {
        return ehdokasId;
    }

    /**
     *
     * @param ehdokasId ehdokkaan id-numero
     */
    public void setEhdokasId(Integer ehdokasId) {
        this.ehdokasId = ehdokasId;
    }

    /**
     *
     * @return
     */
    public String getSukunimi() {
        return sukunimi;
    }

    /**
     *
     * @param sukunimi
     */
    public void setSukunimi(String sukunimi) {
        this.sukunimi = sukunimi;
    }

    /**
     *
     * @return
     */
    public String getEtunimi() {
        return etunimi;
    }

    /**
     *
     * @param etunimi
     */
    public void setEtunimi(String etunimi) {
        this.etunimi = etunimi;
    }

    /**
     *
     * @return
     */
    public String getPuolue() {
        return puolue;
    }

    /**
     *
     * @param puolue
     */
    public void setPuolue(String puolue) {
        this.puolue = puolue;
    }

    /**
     *
     * @return
     */
    public String getKotipaikkakunta() {
        return kotipaikkakunta;
    }

    /**
     *
     * @param kotipaikkakunta
     */
    public void setKotipaikkakunta(String kotipaikkakunta) {
        this.kotipaikkakunta = kotipaikkakunta;
    }

    /**
     *
     * @return
     */
    public Integer getIkä() {
        return ikä;
    }

    /**
     *
     * @param ikä
     */
    public void setIkä(Integer ikä) {
        this.ikä = ikä;
    }

    /**
     *
     * @return
     */
    public String getMiksiEduskuntaan() {
        return miksiEduskuntaan;
    }

    /**
     *
     * @param miksiEduskuntaan
     */
    public void setMiksiEduskuntaan(String miksiEduskuntaan) {
        this.miksiEduskuntaan = miksiEduskuntaan;
    }

    /**
     *
     * @return
     */
    public String getMitaAsioitaHaluatEdistaa() {
        return mitaAsioitaHaluatEdistaa;
    }

    /**
     *
     * @param mitaAsioitaHaluatEdistaa
     */
    public void setMitaAsioitaHaluatEdistaa(String mitaAsioitaHaluatEdistaa) {
        this.mitaAsioitaHaluatEdistaa = mitaAsioitaHaluatEdistaa;
    }

    /**
     *
     * @return
     */
    public String getAmmatti() {
        return ammatti;
    }

    /**
     *
     * @param ammatti
     */
    public void setAmmatti(String ammatti) {
        this.ammatti = ammatti;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ehdokasId != null ? ehdokasId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ehdokkaat)) {
            return false;
        }
        Ehdokkaat other = (Ehdokkaat) object;
        return !((this.ehdokasId == null && other.ehdokasId != null) || (this.ehdokasId != null && !this.ehdokasId.equals(other.ehdokasId)));
    }

    @Override
    public String toString() {
        return "persist.Ehdokkaat[ ehdokasId=" + ehdokasId + " ]";
    }
    private static final Logger LOG = Logger.getLogger(Ehdokkaat.class.getName());
    
}
