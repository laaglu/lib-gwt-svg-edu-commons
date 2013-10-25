package org.vectomatic.svg.edu.client.commons;

import org.vectomatic.dom.svg.OMNode;

import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Node;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.dom.client.Text;
import com.google.gwt.event.dom.client.HasMouseDownHandlers;
import com.google.gwt.event.dom.client.HasTouchStartHandlers;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.TouchStartEvent;
import com.google.gwt.event.dom.client.TouchStartHandler;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

/**
 * A widget to display and allow picking of difficulty levels
 * @author laaglu
 */
public class DifficultyPicker extends Widget implements HasValueChangeHandlers<Integer>, HasMouseDownHandlers, HasTouchStartHandlers, IsWidget {
	/**
	 * True if the picker handles events, false otherwise
	 */
	private boolean enabled;
	
	/**
	 * The number of difficulty levels
	 */
	private int count;
	
	public DifficultyPicker() {
		enabled = true;
		setElement(Document.get().createDivElement());
		getElement().setClassName(CommonBundle.INSTANCE.mediaQueries().navigationPanelDifficultyPicker());
		addMouseDownHandler(new MouseDownHandler() {
			@Override
			public void onMouseDown(MouseDownEvent event) {
				handleEvent(event.getNativeEvent().getEventTarget().<Element>cast());
			}
		});
		addTouchStartHandler(new TouchStartHandler() {
			@Override
			public void onTouchStart(TouchStartEvent event) {
				handleEvent(event.getNativeEvent().getEventTarget().<Element>cast());
			}
		});
		setCount(3);
	}

	private static boolean isDescendantOf(Element parent, Element element) {
		while (element != null) {
			if (element == parent) {
				return true;
			}
			element = element.getParentElement();
		}
		return false;
	}
	
	private void handleEvent(Element target) {
		if (enabled) {
			Element root = getElement();
			NodeList<Node> difficulties = root.getChildNodes();
			for (int i = 0, length = difficulties.getLength(); i < length; i++) {
				if (isDescendantOf(difficulties.getItem(i).<Element>cast(), target)) {
					setDifficulty(i);
					ValueChangeEvent.fire(this, i);
					break;
				}
			}
		}
	}

	@Override
	public HandlerRegistration addTouchStartHandler(TouchStartHandler handler) {
		sinkEvents(Event.getTypeInt(TouchStartEvent.getType().getName()));
	    return OMNode.getEventBus().addHandlerToSource(TouchStartEvent.getType(), this, handler);
	}

	@Override
	public HandlerRegistration addMouseDownHandler(MouseDownHandler handler) {
		sinkEvents(Event.getTypeInt(MouseDownEvent.getType().getName()));
	    return OMNode.getEventBus().addHandlerToSource(MouseDownEvent.getType(), this, handler);
	}

	public int getDifficulty() {
		Element root = getElement();
		NodeList<Node> difficulties = root.getChildNodes();
		for (int i = 0, length = difficulties.getLength(); i < length; i++) {
			Element difficulty = difficulties.getItem(i).<Element>cast();
			if (difficulty.getClassName().contains(CommonBundle.INSTANCE.mediaQueries().difficultyPickerSelected())) {
				return i;
			}
		}
		return 0;
	}
	
	public void setDifficulty(int value) {
		Element root = getElement();
		NodeList<Node> difficulties = root.getChildNodes();
		for (int i = 0, length = difficulties.getLength(); i < length; i++) {
			Element difficulty = difficulties.getItem(i).<Element>cast();
			if (i == value) {
				difficulty.addClassName(CommonBundle.INSTANCE.mediaQueries().difficultyPickerSelected());
			} else {
				difficulty.removeClassName(CommonBundle.INSTANCE.mediaQueries().difficultyPickerSelected());
			}
		}
	}
	
	@Override
	public HandlerRegistration addValueChangeHandler(ValueChangeHandler<Integer> handler) {		
		return OMNode.getEventBus().addHandlerToSource(ValueChangeEvent.getType(), this, handler);
	}

	@Override
	public void fireEvent(GwtEvent<?> event) {
		OMNode.getEventBus().fireEventFromSource(event, this);
	}

	public boolean isEnabled() {
		return enabled;
	}
	
	public void setEnabled(boolean b) {
		enabled = b;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
		update();
	}
	
	public void update() {
		// Remove previous child nodes
		Element root = getElement();
		Node node = root.getFirstChild();
		while(node != null) {
			Node sibling = node.getNextSibling();
			node = root.removeChild(node).getNextSibling();
			node = sibling;
		}
		
		Document document = Document.get();
		for (int i = 0; i < count; i++) {
			DivElement div1 = document.createDivElement();
			DivElement div2 = document.createDivElement();
			Text text = document.createTextNode(Integer.toString(i + 1));
			div2.appendChild(text);
			div1.appendChild(div2);
			root.appendChild(div1);
		}
		setDifficulty(0);
	}
}
