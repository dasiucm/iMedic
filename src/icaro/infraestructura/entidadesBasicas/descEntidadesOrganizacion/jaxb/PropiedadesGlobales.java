//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-147
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2012.07.04 at 04:19:28 PM CEST
//

package icaro.infraestructura.entidadesBasicas.descEntidadesOrganizacion.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for PropiedadesGlobales complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="PropiedadesGlobales">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="intervaloMonitorizacionGestores" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="activarPanelTrazasDebug" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="listaPropiedades" type="{urn:icaro:aplicaciones:descripcionOrganizaciones}ListaPropiedades" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PropiedadesGlobales", propOrder = {
		"intervaloMonitorizacionGestores", "activarPanelTrazasDebug",
		"listaPropiedades" })
public class PropiedadesGlobales {

	protected Integer intervaloMonitorizacionGestores;
	protected Boolean activarPanelTrazasDebug;
	protected ListaPropiedades listaPropiedades;

	/**
	 * Gets the value of the intervaloMonitorizacionGestores property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getIntervaloMonitorizacionGestores() {
		return intervaloMonitorizacionGestores;
	}

	/**
	 * Sets the value of the intervaloMonitorizacionGestores property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setIntervaloMonitorizacionGestores(Integer value) {
		this.intervaloMonitorizacionGestores = value;
	}

	/**
	 * Gets the value of the activarPanelTrazasDebug property.
	 * 
	 * @return possible object is {@link Boolean }
	 * 
	 */
	public Boolean isActivarPanelTrazasDebug() {
		return activarPanelTrazasDebug;
	}

	/**
	 * Sets the value of the activarPanelTrazasDebug property.
	 * 
	 * @param value
	 *            allowed object is {@link Boolean }
	 * 
	 */
	public void setActivarPanelTrazasDebug(Boolean value) {
		this.activarPanelTrazasDebug = value;
	}

	/**
	 * Gets the value of the listaPropiedades property.
	 * 
	 * @return possible object is {@link ListaPropiedades }
	 * 
	 */
	public ListaPropiedades getListaPropiedades() {
		return listaPropiedades;
	}

	/**
	 * Sets the value of the listaPropiedades property.
	 * 
	 * @param value
	 *            allowed object is {@link ListaPropiedades }
	 * 
	 */
	public void setListaPropiedades(ListaPropiedades value) {
		this.listaPropiedades = value;
	}

}
