//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.2-147
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2012.07.04 at 04:19:28 PM CEST
//

package icaro.infraestructura.entidadesBasicas.descEntidadesOrganizacion.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for InstanciaGestor complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType name="InstanciaGestor">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:icaro:aplicaciones:descripcionOrganizaciones}Instancia">
 *       &lt;sequence>
 *         &lt;element name="componentesGestionados" type="{urn:icaro:aplicaciones:descripcionOrganizaciones}ComponentesGestionados"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InstanciaGestor", propOrder = { "componentesGestionados" })
public class InstanciaGestor extends Instancia {

	@XmlElement(required = true)
	protected ComponentesGestionados componentesGestionados;

	/**
	 * Gets the value of the componentesGestionados property.
	 * 
	 * @return possible object is {@link ComponentesGestionados }
	 * 
	 */
	public ComponentesGestionados getComponentesGestionados() {
		return componentesGestionados;
	}

	/**
	 * Sets the value of the componentesGestionados property.
	 * 
	 * @param value
	 *            allowed object is {@link ComponentesGestionados }
	 * 
	 */
	public void setComponentesGestionados(ComponentesGestionados value) {
		this.componentesGestionados = value;
	}

}
