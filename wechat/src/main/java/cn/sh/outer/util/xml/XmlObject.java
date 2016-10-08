package cn.sh.outer.util.xml;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.dom4j.Attribute;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * 根据该对象可以构造Xml字符串
 * @author zhangpeng
 * @modify yan
 *
 */
public class XmlObject {
	private static String HEAD = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
	private String name;
	private Object value;
	private boolean isCDATA;
	private Map<String, Object> attributes;
	private List<XmlObject> subXmlObjects;

	/**
	 * 根据name构造XmlObject
	 * 
	 * @param name
	 *            生成xml时标签名，如name="html"，则生成xml为<html/>
	 */
	public XmlObject(String name) {
		this.name = name;
	}
	
	/**
	 * 根据name构造XmlObject
	 * 
	 * @param name 生成xml时标签名
	 * @param value 内容
	 * @author yan
	 */
	public XmlObject(String name, Object value) {
		this.name = name;
		this.value = value;
	}
	
	/**
	 * 根据name构造XmlObject
	 * 
	 * @param name 生成xml时标签名
	 * @param value 内容
	 * @param subXmlObjects 子节点
	 * @author yan
	 */
	public XmlObject(String name, Object value, XmlObject ... subXmlObjects) {
		this.name = name;
		this.value = value;
		if (this.subXmlObjects == null) {
			this.subXmlObjects = new ArrayList<XmlObject>();
		}
		Collections.addAll(this.subXmlObjects, subXmlObjects);
	}

	public XmlObject(String name, String value, boolean isCDATA) {
		this.name = name;
		this.value = value;
		this.isCDATA = isCDATA;
	}

	/**
	 * 获得当前对象的名称
	 * 
	 * @return 返回当前对象的名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置当前对象的名称
	 * 
	 * @param name
	 *            给定名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获得当前对象的值
	 * 
	 * @return 返回当前对象的值
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * 设置当前对象的值
	 * 
	 * @param value
	 *            给定值
	 */
	public void setValue(Object value) {
		this.value = value;
	}

	/**
	 * 为当前XmlObject添加属性
	 * 
	 * @param name
	 *            属性名
	 * @param value
	 *            属性值
	 */
	public void addAttribute(String name, Object value) {
		if (attributes == null) {
			attributes = new LinkedHashMap<String, Object>();
		}
		if (name != null && !name.trim().equals("") && !name.equals(this.name)) {
			attributes.put(name, value);
		}
	}
	
	/**
	 * 为当前XmlObject添加属性
	 * 
	 * @param name
	 *            属性名
	 * @param value
	 *            属性值
	 */
	public void setAttribute(String name, Object value) {
		addAttribute(name, value);
	}

	/**
	 * 根据属性名称获得当前XmlObject对象的属性值
	 * 
	 * @param name
	 *            属性名称
	 * @return 属性值
	 */
	public Object getAttributeValue(String name) {
		return getAttributeValue(name, null);
	}

	/**
	 * 根据属性名称获得当前XmlObject对象的属性值
	 * 
	 * @param name
	 *            属性名称
	 * @param defaultValue
	 *            默认值
	 * @return 若属性存在，则返回属性值，否则返回默认值
	 */
	public Object getAttributeValue(String name, Object defaultValue) {
		Object value = attributes.get(name);
		return value == null ? defaultValue : value;
	}

	/**
	 * 为当前XmlObject对象添加子XmlObject对象
	 * 
	 * @param xmlObject
	 *            给定XmlObject对象
	 */
	public XmlObject addSubXmlObject(XmlObject xmlObject) {
		if (subXmlObjects == null) {
			subXmlObjects = new ArrayList<XmlObject>();
		}
		if (xmlObject != null) {
			subXmlObjects.add(xmlObject);
		}
		return xmlObject;
	}
	/**
	 * 为当前XmlObject对象添加子XmlObject对象
	 * @param xmlObjects 给定XmlObject对象集合
	 * @author yan
	 */
	public void addAllSubXmlObject(Collection<XmlObject> xmlObjects) {
		if (xmlObjects != null) {
			if (subXmlObjects == null) {
				subXmlObjects = new ArrayList<XmlObject>();
			}
			subXmlObjects.addAll(xmlObjects);
		}
	}
	
	public List<XmlObject> getSubXmlObjects(String name) {
		List<XmlObject> xmlObjects = new ArrayList<XmlObject>();
		for (XmlObject temp: subXmlObjects) {
			if (temp.getName().equals(name)) {
				xmlObjects.add(temp);
			}
		}
		return xmlObjects;
	}
	
	public String getChildValue(String key) {
		if(subXmlObjects == null || subXmlObjects.isEmpty())
			return null;
		XmlObject object = getUniqueSubXmlObject(key);
		return object != null ? (String)object.value : null;
	}
	
	public XmlObject getUniqueSubXmlObject(String name) {
		for (XmlObject temp: subXmlObjects) {
			if (temp.getName().equals(name)) {
				return temp;
			}
		}
		return null;
	}

	/**
	 * 构造当前对象的压缩XML字符串
	 * 
	 * @return XML字符串
	 */
	public String toCompactXml() {
		return HEAD + getNoHeadXml("", "");
	}

	/**
	 * 根据格式化留白("\t")和默认的行分隔符("\t")构造当前对象的XML字符串
	 * 
	 * @return XML字符串
	 */
	public String toFormatXml() {
		return HEAD + getNoHeadXml("\t", "\n");
	}

	/**
	 * 根据格式化留白和默认的行分隔符构("\n")造当前对象的XML字符串
	 * 
	 * @param blank
	 *            格式化留白
	 * @return XML字符串
	 */
	public String toFormatXml(String blank) {
		return HEAD + getNoHeadXml(blank, "\n");
	}

	/**
	 * 根据格式化留白和行分隔符构造当前对象的无头的XML字符串
	 * 
	 * @param blank
	 *            格式化留白
	 * @param split
	 *            行分隔符
	 * @return 无头的XML字符串
	 */
	private String getNoHeadXml(String blank, String split) {
		return getNoHeadXml(blank, split, 0);
	}

	private String getNoHeadXml(String blank, String split, int count) {
		String blanks = "";
		for (int i = 0; i < count; i++) {
			blanks += blank;
		}
		StringBuffer sb = new StringBuffer();
		sb.append(count == 0 ? split : "");
		sb.append(blanks + "<" + name);
		if (attributes != null) {
			Set<Entry<String, Object>> set = attributes.entrySet();
			for (Entry<String, Object> entry : set) {
				String tempName = entry.getKey();
				Object tempValue = entry.getValue();
				if (tempName != null && tempValue != null) {
					sb.append(" " + tempName + "=\"" + tempValue + "\"");
				}
			}
		}
		if (subXmlObjects == null) {
			if (value == null) {
				sb.append("/>" + split);
			} else {
				sb.append(">").append(isCDATA ? "<![CDATA[" : "");
				sb.append(value);
				sb.append(isCDATA ? "]]>" : "").append("</" + name + ">" + split);
			}
		} else {
			sb.append(">" + split);
			Iterator<XmlObject> iterator = subXmlObjects.iterator();
			count += 1;
			while (iterator.hasNext()) {
				XmlObject xmlObject = iterator.next();
				sb.append(xmlObject.getNoHeadXml(blank, split, count));
			}
			sb.append(blanks + "</" + name + ">" + split);
		}
		return sb.toString();
	}
	
	/**
	 * @author yan
	 */
	public void clear(){
		this.subXmlObjects = null;
		this.attributes = null;
	}

	/**
	 * 字符串转换XmlObject
	 * @author yan
	 * @param xml
	 * @return
	 * @throws Exception
	 */
	public static XmlObject parse(String xml) throws Exception{
		if(xml == null || "".equals(xml))
			return null;
		Element root = DocumentHelper.parseText(xml).getRootElement();
		XmlObject rootObj = new XmlObject(root.getName(), root.getText());
		
		rootObj.addAllSubXmlObject(list(root));
		return rootObj;
	}

	private static Collection<XmlObject> list(Element parentElement) {
		List<Element> childs = parentElement.elements();
		if(childs.size() == 0) {
			return null;
		}
		List<XmlObject> objects = new ArrayList<XmlObject>();
		for (Element child : childs) {
			XmlObject object = new XmlObject(child.getName(), child.getText());
			List childAttrList = child.attributes();
			if(childAttrList != null && childAttrList.size() > 0){
				for(int i = 0;i < childAttrList.size(); i++){
					Attribute attrTemp = (Attribute) childAttrList.get(i);
					object.setAttribute(attrTemp.getName(), attrTemp.getValue());
				}
			}
			object.addAllSubXmlObject(list(child));
			objects.add(object);
		}
		return objects;
	}

	public boolean containsChild(String key) {
		return getUniqueSubXmlObject(key) != null;
	}

	/**
	 * @param isCDATA the isCDATA to set
	 */
	public void setCDATA(boolean isCDATA) {
		this.isCDATA = isCDATA;
	}
	
	
}