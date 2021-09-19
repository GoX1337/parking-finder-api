package com.gox.parking.finder.api.jaxb.wfs;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;

@XmlRootElement(name = "ST_PARK_P")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class StParkP {

    @XmlElement(namespace = "http://www.opengis.net/gml")
    private BoundedBy boundedBy;

    @XmlElement(namespace = "http://data.bordeaux-metropole.fr/wfs")
    private Geometry geometry;

    @XmlElement(name="GID", namespace = "http://data.bordeaux-metropole.fr/wfs")
    private Long gid;

    @XmlElement(name="GEOM_O", namespace = "http://data.bordeaux-metropole.fr/wfs")
    private String geom0;

    @XmlElement(name="IDENT", namespace = "http://data.bordeaux-metropole.fr/wfs")
    private String ident;

    @XmlElement(name="NOM", namespace = "http://data.bordeaux-metropole.fr/wfs")
    private String name;

    @XmlElement(name="LIBRES", namespace = "http://data.bordeaux-metropole.fr/wfs")
    private Integer libres;

    @XmlElement(name="TOTAL", namespace = "http://data.bordeaux-metropole.fr/wfs")
    private Integer total;

    @XmlElement(name="PREPAYE", namespace = "http://data.bordeaux-metropole.fr/wfs")
    private Boolean prepaye;

    @XmlElement(name="ETAT", namespace = "http://data.bordeaux-metropole.fr/wfs")
    private String etat;

    @XmlElement(name="CONNECTE", namespace = "http://data.bordeaux-metropole.fr/wfs")
    private Boolean connecte;

    @XmlElement(name="URL", namespace = "http://data.bordeaux-metropole.fr/wfs")
    private String url;

    @XmlElement(name="TA_TITUL", namespace = "http://data.bordeaux-metropole.fr/wfs")
    private String taTitul;

    @XmlElement(name="TA_NTITUL", namespace = "http://data.bordeaux-metropole.fr/wfs")
    private String taNTitul;

    @XmlElement(name="TH_QUAR", namespace = "http://data.bordeaux-metropole.fr/wfs")
    private String thQuar;

    @XmlElement(name="TH_DEMI", namespace = "http://data.bordeaux-metropole.fr/wfs")
    private String thDemi;

    @XmlElement(name="TH_HEUR", namespace = "http://data.bordeaux-metropole.fr/wfs")
    private String thHeur;

    @XmlElement(name="TH_2", namespace = "http://data.bordeaux-metropole.fr/wfs")
    private String th2;

    @XmlElement(name="TH_3", namespace = "http://data.bordeaux-metropole.fr/wfs")
    private String th3;

    @XmlElement(name="TH_4", namespace = "http://data.bordeaux-metropole.fr/wfs")
    private String th4;

    @XmlElement(name="TH_10", namespace = "http://data.bordeaux-metropole.fr/wfs")
    private String th10;

    @XmlElement(name="TH_24", namespace = "http://data.bordeaux-metropole.fr/wfs")
    private String th24;

    @XmlElement(name="TH_NUIT", namespace = "http://data.bordeaux-metropole.fr/wfs")
    private String thNuit;

    @XmlElement(name="TA_RESMOI", namespace = "http://data.bordeaux-metropole.fr/wfs")
    private String taResmoi;

    @XmlElement(name="TA_NRES7J", namespace = "http://data.bordeaux-metropole.fr/wfs")
    private String thNres7j;

    @XmlElement(name="TA_MOIMOT", namespace = "http://data.bordeaux-metropole.fr/wfs")
    private String moimot;

    @XmlElement(name="TA_MOIVEL", namespace = "http://data.bordeaux-metropole.fr/wfs")
    private String moivel;

    @XmlElement(name="TV_1H", namespace = "http://data.bordeaux-metropole.fr/wfs")
    private String tv1h;

    @XmlElement(name="INFOR", namespace = "http://data.bordeaux-metropole.fr/wfs")
    private String infor;

    @XmlElement(name="PROPR", namespace = "http://data.bordeaux-metropole.fr/wfs")
    private String propr;

    @XmlElement(name="TITUL", namespace = "http://data.bordeaux-metropole.fr/wfs")
    private String titul;

    @XmlElement(name="EXPLOIT", namespace = "http://data.bordeaux-metropole.fr/wfs")
    private String exploit;

    @XmlElement(name="TYPGEST", namespace = "http://data.bordeaux-metropole.fr/wfs")
    private String typgest;

    @XmlElement(name="SECTEUR", namespace = "http://data.bordeaux-metropole.fr/wfs")
    private String secteur;

    @XmlElement(name="INSEE", namespace = "http://data.bordeaux-metropole.fr/wfs")
    private String insee;

    @XmlElement(name="AN_SERV", namespace = "http://data.bordeaux-metropole.fr/wfs")
    private String anServ;

    @XmlElement(name="TYPE", namespace = "http://data.bordeaux-metropole.fr/wfs")
    private String type;

    @XmlElement(name="NB_NIV", namespace = "http://data.bordeaux-metropole.fr/wfs")
    private Integer nbNiv;

    @XmlElement(name="NP_HGINF", namespace = "http://data.bordeaux-metropole.fr/wfs")
    private Integer npHginf;

    @XmlElement(name="NP_HGSUP", namespace = "http://data.bordeaux-metropole.fr/wfs")
    private Integer npHgsup;

    @XmlElement(name="NP_FOURR", namespace = "http://data.bordeaux-metropole.fr/wfs")
    private Integer npFourr;

    @XmlElement(name="NP_GLOBAL", namespace = "http://data.bordeaux-metropole.fr/wfs")
    private Integer npGlobal;

    @XmlElement(name="NP_TOTAL", namespace = "http://data.bordeaux-metropole.fr/wfs")
    private Integer npTotal;

    @XmlElement(name="NP_PR", namespace = "http://data.bordeaux-metropole.fr/wfs")
    private Integer npPr;

    @XmlElement(name="NP_PMR", namespace = "http://data.bordeaux-metropole.fr/wfs")
    private Integer npPmr;

    @XmlElement(name="NP_VLE", namespace = "http://data.bordeaux-metropole.fr/wfs")
    private Integer npVle;

    @XmlElement(name="NP_MOBALT", namespace = "http://data.bordeaux-metropole.fr/wfs")
    private Integer npMobalt;

    @XmlElement(name="NP_COVOIT", namespace = "http://data.bordeaux-metropole.fr/wfs")
    private Integer npCovoit;

    @XmlElement(name="NP_STLAV", namespace = "http://data.bordeaux-metropole.fr/wfs")
    private Integer npStlav;

    @XmlElement(name="NP_2RMOT", namespace = "http://data.bordeaux-metropole.fr/wfs")
    private Integer np2rmot;

    @XmlElement(name="NP_2RELE", namespace = "http://data.bordeaux-metropole.fr/wfs")
    private Integer np2rele;

    @XmlElement(name="NP_VELTOT", namespace = "http://data.bordeaux-metropole.fr/wfs")
    private Integer npVeltot;

    @XmlElement(name="NP_VELEC", namespace = "http://data.bordeaux-metropole.fr/wfs")
    private Integer npVelec;

    @XmlElement(name="ADRESSE", namespace = "http://data.bordeaux-metropole.fr/wfs")
    private String adresse;

    @XmlElement(name="GABARI_MAX", namespace = "http://data.bordeaux-metropole.fr/wfs")
    private String gabaritMax;

    @XmlElement(name="GABARI_STD", namespace = "http://data.bordeaux-metropole.fr/wfs")
    private String gabaritStd;

    @XmlElement(name="TA_TYPE", namespace = "http://data.bordeaux-metropole.fr/wfs")
    private String taType;

    @XmlElement(name="TA_HANDI", namespace = "http://data.bordeaux-metropole.fr/wfs")
    private String taHandi;

    @XmlElement(name="SIRET", namespace = "http://data.bordeaux-metropole.fr/wfs")
    private String siret;

    @XmlElement(name="CDATE", namespace = "http://data.bordeaux-metropole.fr/wfs")
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date creationDate;

    @XmlElement(name="MDATE", namespace = "http://data.bordeaux-metropole.fr/wfs")
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date modificationDate;
}
