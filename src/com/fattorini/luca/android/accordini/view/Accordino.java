package com.fattorini.luca.android.accordini.view;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.fattorini.luca.android.accordini.R;

/**
 * 
 * @author luca-fattorini
 * 
 */
public class Accordino extends RelativeLayout {
	private static final String TAG = Accordino.class.getName();

	// defaults
	private int layoutId = R.layout.view_accordino;
	private int stateIconId = R.id.accordino_header_icon;
	private int bodyId = R.id.accordino_body;
	private int bodyContentId = R.id.accordino_body_content;
	private int headerId = R.id.accordino_header;
	private int headerContentId = R.id.accordino_header_content;
	private int stateIconCollapsedId = R.drawable.accordino_collapsed;
	private int stateIconExpandedId = R.drawable.accordino_expanded;

	private List<OnStateChangeListener> listeners;

	protected boolean expanded;

	private int bodyResLayout;
	private int headerResLayout;

	protected ImageView stateIcon;
	protected LinearLayout body;
	protected LinearLayout bodyContent;
	protected LinearLayout header;
	protected LinearLayout headerContent;

	public Accordino(Context context) {
		this(context, null);
	}

	public Accordino(final Context context, final int layoutId) {
		this(context, null);
		this.layoutId = layoutId;
	}

	public Accordino(final Context context, final AttributeSet attrs) {
		super(context, attrs);
		initAttributes(context, attrs);
		initLayout();
	}

	public Accordino(final Context context, final AttributeSet attrs, final int defStyle) {
		super(context, attrs, defStyle);
		initAttributes(context, attrs);
		initLayout();
	}

	private void initLayout() {
		inflate(getContext(), layoutId, this);
		listeners = new ArrayList<OnStateChangeListener>();

		stateIcon = (ImageView) findViewById(stateIconId);
		body = (LinearLayout) findViewById(bodyId);
		bodyContent = (LinearLayout) findViewById(bodyContentId);
		header = (LinearLayout) findViewById(headerId);
		headerContent = (LinearLayout) findViewById(headerContentId);

		templateValidation();

		if (bodyResLayout != 0)
			setBody(bodyResLayout);

		if (headerResLayout != 0)
			setHeader(headerResLayout);

		if (expanded)
			open();
		else
			collapse();

		bodyContent.setVisibility(expanded ? View.VISIBLE : View.GONE);

		header.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				toggle();
			}
		});

	}

	/**
	 * Toggle method
	 */
	protected void toggle() {
		if (expanded)
			collapse();
		else
			open();

		bodyContent.setVisibility(expanded ? View.VISIBLE : View.GONE);
	}

	private void open() {
		onExpand();
		stateIcon.setImageResource(stateIconExpandedId);
		expanded = true;
	}

	private void collapse() {
		onCollapse();
		expanded = false;
		stateIcon.setImageResource(stateIconCollapsedId);
	}

	/**
	 * On Expand method, called when Accordino is expanding
	 */
	protected void onExpand() {
		Log.v(TAG, "onExpand");
		for (OnStateChangeListener listener : listeners)
			listener.onExpand(this);
	}

	/**
	 * On collapse method, called when Accordino is collapsing
	 */
	protected void onCollapse() {
		Log.v(TAG, "onCollapse");
		for (OnStateChangeListener listener : listeners)
			listener.onCollapse(this);
	}

	protected void initAttributes(final Context context, final AttributeSet attrs) {
		if (attrs != null) {
			TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.Accordable);
			stateIconExpandedId = a.getResourceId(R.styleable.Accordable_expandedResid, stateIconExpandedId);
			stateIconCollapsedId = a.getResourceId(R.styleable.Accordable_collapsedResid, stateIconCollapsedId);
			expanded = a.getBoolean(R.styleable.Accordable_expanded, false);
			bodyResLayout = a.getResourceId(R.styleable.Accordable_bodyView, 0);
			headerResLayout = a.getResourceId(R.styleable.Accordable_headerView, 0);
			a.recycle();
		}
	}

	/**
	 * Set a custom View for header container
	 * 
	 * @param view
	 */
	public void setBody(final View view) {
		bodyContent.removeAllViews();
		bodyContent.addView(view);
	}

	/**
	 * Set a custom layout for body container
	 * 
	 * @param layoutId
	 */
	public void setBody(final int layoutId) {
		LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(layoutId, null, false);
		bodyContent.removeAllViews();
		bodyContent.addView(view);
	}

	/**
	 * Set a custom View for header container
	 * 
	 * @param view
	 */
	public void setHeader(final View view) {
		headerContent.removeAllViews();
		headerContent.addView(view);
	}

	/**
	 * Set a custom layout for header container
	 * 
	 * @param layoutId
	 */
	public void setHeader(final int layoutId) {
		LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(layoutId, null, false);
		headerContent.removeAllViews();
		headerContent.addView(view);
	}

	/**
	 * 
	 * @param listener
	 * @return if listener have been correctly added
	 */
	public boolean setOnStateChangeListener(final OnStateChangeListener listener) {
		return listeners.add(listener);
	}

	/**
	 * 
	 * @param listener
	 * @return if listener have been correctly removed
	 */
	public boolean removeOnStateChangeListenr(final OnStateChangeListener listener) {
		return listeners.remove(listener);
	}

	/**
	 * Base Template Validation
	 */
	private void templateValidation() {
		if (stateIcon == null)
			throw new IllegalArgumentException("No R.id.accordino_header_icon");
		if (bodyContent == null)
			throw new IllegalArgumentException("No R.id.accordino_body");
		if (headerContent == null)
			throw new IllegalArgumentException("No R.id.accordino_header_content");
	}
}
