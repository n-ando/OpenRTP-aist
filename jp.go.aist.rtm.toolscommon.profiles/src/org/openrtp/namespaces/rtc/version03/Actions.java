
package org.openrtp.namespaces.rtc.version03;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for actions complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="actions">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="OnInitialize" type="{http://www.openrtp.org/namespaces/rtc}action_status" minOccurs="0"/>
 *         &lt;element name="OnFinalize" type="{http://www.openrtp.org/namespaces/rtc}action_status" minOccurs="0"/>
 *         &lt;element name="OnStartup" type="{http://www.openrtp.org/namespaces/rtc}action_status" minOccurs="0"/>
 *         &lt;element name="OnShutdown" type="{http://www.openrtp.org/namespaces/rtc}action_status" minOccurs="0"/>
 *         &lt;element name="OnActivated" type="{http://www.openrtp.org/namespaces/rtc}action_status" minOccurs="0"/>
 *         &lt;element name="OnDeactivated" type="{http://www.openrtp.org/namespaces/rtc}action_status" minOccurs="0"/>
 *         &lt;element name="OnAborting" type="{http://www.openrtp.org/namespaces/rtc}action_status" minOccurs="0"/>
 *         &lt;element name="OnError" type="{http://www.openrtp.org/namespaces/rtc}action_status" minOccurs="0"/>
 *         &lt;element name="OnReset" type="{http://www.openrtp.org/namespaces/rtc}action_status" minOccurs="0"/>
 *         &lt;element name="OnExecute" type="{http://www.openrtp.org/namespaces/rtc}action_status" minOccurs="0"/>
 *         &lt;element name="OnStateUpdate" type="{http://www.openrtp.org/namespaces/rtc}action_status" minOccurs="0"/>
 *         &lt;element name="OnRateChanged" type="{http://www.openrtp.org/namespaces/rtc}action_status" minOccurs="0"/>
 *         &lt;element name="OnAction" type="{http://www.openrtp.org/namespaces/rtc}action_status" minOccurs="0"/>
 *         &lt;element name="OnModeChanged" type="{http://www.openrtp.org/namespaces/rtc}action_status" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "actions", propOrder = {
    "onInitialize",
    "onFinalize",
    "onStartup",
    "onShutdown",
    "onActivated",
    "onDeactivated",
    "onAborting",
    "onError",
    "onReset",
    "onExecute",
    "onStateUpdate",
    "onRateChanged",
    "onAction",
    "onModeChanged"
})
public class Actions {

    @XmlElement(name = "OnInitialize")
    protected ActionStatus onInitialize;
    @XmlElement(name = "OnFinalize")
    protected ActionStatus onFinalize;
    @XmlElement(name = "OnStartup")
    protected ActionStatus onStartup;
    @XmlElement(name = "OnShutdown")
    protected ActionStatus onShutdown;
    @XmlElement(name = "OnActivated")
    protected ActionStatus onActivated;
    @XmlElement(name = "OnDeactivated")
    protected ActionStatus onDeactivated;
    @XmlElement(name = "OnAborting")
    protected ActionStatus onAborting;
    @XmlElement(name = "OnError")
    protected ActionStatus onError;
    @XmlElement(name = "OnReset")
    protected ActionStatus onReset;
    @XmlElement(name = "OnExecute")
    protected ActionStatus onExecute;
    @XmlElement(name = "OnStateUpdate")
    protected ActionStatus onStateUpdate;
    @XmlElement(name = "OnRateChanged")
    protected ActionStatus onRateChanged;
    @XmlElement(name = "OnAction")
    protected ActionStatus onAction;
    @XmlElement(name = "OnModeChanged")
    protected ActionStatus onModeChanged;

    /**
     * Gets the value of the onInitialize property.
     * 
     * @return
     *     possible object is
     *     {@link ActionStatus }
     *     
     */
    public ActionStatus getOnInitialize() {
        return onInitialize;
    }

    /**
     * Sets the value of the onInitialize property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActionStatus }
     *     
     */
    public void setOnInitialize(ActionStatus value) {
        this.onInitialize = value;
    }

    /**
     * Gets the value of the onFinalize property.
     * 
     * @return
     *     possible object is
     *     {@link ActionStatus }
     *     
     */
    public ActionStatus getOnFinalize() {
        return onFinalize;
    }

    /**
     * Sets the value of the onFinalize property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActionStatus }
     *     
     */
    public void setOnFinalize(ActionStatus value) {
        this.onFinalize = value;
    }

    /**
     * Gets the value of the onStartup property.
     * 
     * @return
     *     possible object is
     *     {@link ActionStatus }
     *     
     */
    public ActionStatus getOnStartup() {
        return onStartup;
    }

    /**
     * Sets the value of the onStartup property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActionStatus }
     *     
     */
    public void setOnStartup(ActionStatus value) {
        this.onStartup = value;
    }

    /**
     * Gets the value of the onShutdown property.
     * 
     * @return
     *     possible object is
     *     {@link ActionStatus }
     *     
     */
    public ActionStatus getOnShutdown() {
        return onShutdown;
    }

    /**
     * Sets the value of the onShutdown property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActionStatus }
     *     
     */
    public void setOnShutdown(ActionStatus value) {
        this.onShutdown = value;
    }

    /**
     * Gets the value of the onActivated property.
     * 
     * @return
     *     possible object is
     *     {@link ActionStatus }
     *     
     */
    public ActionStatus getOnActivated() {
        return onActivated;
    }

    /**
     * Sets the value of the onActivated property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActionStatus }
     *     
     */
    public void setOnActivated(ActionStatus value) {
        this.onActivated = value;
    }

    /**
     * Gets the value of the onDeactivated property.
     * 
     * @return
     *     possible object is
     *     {@link ActionStatus }
     *     
     */
    public ActionStatus getOnDeactivated() {
        return onDeactivated;
    }

    /**
     * Sets the value of the onDeactivated property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActionStatus }
     *     
     */
    public void setOnDeactivated(ActionStatus value) {
        this.onDeactivated = value;
    }

    /**
     * Gets the value of the onAborting property.
     * 
     * @return
     *     possible object is
     *     {@link ActionStatus }
     *     
     */
    public ActionStatus getOnAborting() {
        return onAborting;
    }

    /**
     * Sets the value of the onAborting property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActionStatus }
     *     
     */
    public void setOnAborting(ActionStatus value) {
        this.onAborting = value;
    }

    /**
     * Gets the value of the onError property.
     * 
     * @return
     *     possible object is
     *     {@link ActionStatus }
     *     
     */
    public ActionStatus getOnError() {
        return onError;
    }

    /**
     * Sets the value of the onError property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActionStatus }
     *     
     */
    public void setOnError(ActionStatus value) {
        this.onError = value;
    }

    /**
     * Gets the value of the onReset property.
     * 
     * @return
     *     possible object is
     *     {@link ActionStatus }
     *     
     */
    public ActionStatus getOnReset() {
        return onReset;
    }

    /**
     * Sets the value of the onReset property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActionStatus }
     *     
     */
    public void setOnReset(ActionStatus value) {
        this.onReset = value;
    }

    /**
     * Gets the value of the onExecute property.
     * 
     * @return
     *     possible object is
     *     {@link ActionStatus }
     *     
     */
    public ActionStatus getOnExecute() {
        return onExecute;
    }

    /**
     * Sets the value of the onExecute property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActionStatus }
     *     
     */
    public void setOnExecute(ActionStatus value) {
        this.onExecute = value;
    }

    /**
     * Gets the value of the onStateUpdate property.
     * 
     * @return
     *     possible object is
     *     {@link ActionStatus }
     *     
     */
    public ActionStatus getOnStateUpdate() {
        return onStateUpdate;
    }

    /**
     * Sets the value of the onStateUpdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActionStatus }
     *     
     */
    public void setOnStateUpdate(ActionStatus value) {
        this.onStateUpdate = value;
    }

    /**
     * Gets the value of the onRateChanged property.
     * 
     * @return
     *     possible object is
     *     {@link ActionStatus }
     *     
     */
    public ActionStatus getOnRateChanged() {
        return onRateChanged;
    }

    /**
     * Sets the value of the onRateChanged property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActionStatus }
     *     
     */
    public void setOnRateChanged(ActionStatus value) {
        this.onRateChanged = value;
    }

    /**
     * Gets the value of the onAction property.
     * 
     * @return
     *     possible object is
     *     {@link ActionStatus }
     *     
     */
    public ActionStatus getOnAction() {
        return onAction;
    }

    /**
     * Sets the value of the onAction property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActionStatus }
     *     
     */
    public void setOnAction(ActionStatus value) {
        this.onAction = value;
    }

    /**
     * Gets the value of the onModeChanged property.
     * 
     * @return
     *     possible object is
     *     {@link ActionStatus }
     *     
     */
    public ActionStatus getOnModeChanged() {
        return onModeChanged;
    }

    /**
     * Sets the value of the onModeChanged property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActionStatus }
     *     
     */
    public void setOnModeChanged(ActionStatus value) {
        this.onModeChanged = value;
    }

}
