package com.appbuilders.pokedexgo;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.AbsoluteLayout;

import java.lang.Object;import java.lang.String;import java.util.ArrayList;

public class SfPanel extends Object {

    public SfPoint point;
    public SfSize size;
    public SfRect frame;
    public SfBox origin;
    public SfBox margin;
    public SfBox padding;
    public int zIndex;
    public int position;
    public int alignment;
    public boolean visible;
    public SfPanel parent;;
    public SfPanel firstChild;
    public SfPanel lastChild;
    public SfPanel prev;
    public SfPanel next;
    public View view;
    public String key;
    public float scrollHeight;
    public boolean scrollHost;
    protected int line;

    public static final int SF_POSITION_RELATIVE = 0;
    public static final int SF_POSITION_ABSOLUTE = 1;
    public static final int SF_POSITION_FIXED    = 2;

    public static final int SF_ALIGNMENT_LEFT    = 0;
    public static final int SF_ALIGNMENT_RIGHT   = 1;
    public static final int SF_ALIGNMENT_CENTER  = 2;

    public static final float SF_UNSET  = -9999;

    public SfPanel() {
        this.point = new SfPoint();
        this.size = new SfSize();
        this.frame = new SfRect();
        this.origin = new SfBox();
        this.margin = new SfBox();
        this.padding = new SfBox();
        this.position = this.SF_POSITION_RELATIVE;
        this.alignment = this.SF_ALIGNMENT_CENTER;
        this.visible = true;
        this.zIndex = 0;
        this.parent = null;
        this.firstChild = null;
        this.lastChild = null;
        this.prev = null;
        this.next = null;
        this.view = null;
        this.key = "";
        this.line = 0;
        this.scrollHeight = 0;
        this.scrollHost = false;
    }

    public SfPanel setSize(float width, float height) {
        this.size.setSize(width, height);
        return this;
    }

    public SfPanel setPosition(int position) {
        this.position = position;
        return this;
    }

    public SfPanel setAlignment(int alignment) {
        this.alignment = alignment;
        return this;
    }

    public SfPanel setOrigin(float top, float right, float bottom, float left) {
        this.origin.setBox(top, right, bottom, left);
        return this;
    }

    public SfPanel setMargin(float top, float right, float bottom, float left) {
        this.margin.setBox(top, right, bottom, left);
        return this;
    }

    public SfPanel setPadding(float top, float right, float bottom, float left) {
        this.padding.setBox(top, right, bottom, left);
        return this;
    }

    public SfPanel setParent(SfPanel parent) {
        this.parent = parent;
        return this;
    }

    public SfPanel getParent() {
        return this.parent;
    }

    public void setView(View view) {
        this.view = view;
    }

    public View getView() {
        return this.view;
    }

    public SfPanel setKey(String key) {
        this.key = key;
        return this;
    }

    public String getKey() {
        return this.key;
    }

    public SfPanel append(SfPanel object) {
        if (object != null) {
            object.setParent(this);
            // Our list logic
            if (this.lastChild == null) {
                this.firstChild = object;
                this.lastChild = object;
            } else {
                object.prev = this.lastChild;
                this.lastChild.next = object;
                this.lastChild = object;
            }
        }
        return this;
    }

    public SfPanel prepend(SfPanel object) {
        if (object != null) {
            object.setParent(this);
            // Our list logic
            if (this.firstChild == null) {
                this.firstChild = object;
                this.lastChild = object;
            } else {
                object.next = this.firstChild;
                this.firstChild.prev = object;
                this.firstChild = object;
            }
        }
        return this;
    }

    public SfPanel find(String key) {
        SfPanel child = this.firstChild;
        SfPanel ret = null;
        while (child != null) {
            if (child.getKey().compareTo(key) == 0) {
                ret = child;
                break;
            } else {
                ret = child.find(key);
                if (ret != null) break;
            }
            child = child.next;
        }
        return ret;
    }

    public SfPanel next() {
        return this.next;
    }

    public SfPanel prev() {
        return this.prev;
    }

    public ArrayList<SfPanel> siblings() {
        ArrayList<SfPanel> siblings = new ArrayList<SfPanel>();
        if (this.parent != null) {
            SfPanel sibling = this.parent.firstChild;
            while (sibling != null) {
                if (sibling == this) continue;
                siblings.add(sibling);
                sibling = sibling.next;
            }
        }
        return siblings;
    }

    public SfPanel closest(String key) {
        SfPanel ret = null;
        if (this.parent != null) {
            if (this.parent.getKey().compareTo(key) == 0) {
                ret = this.parent;
            } else {
                ret = this.parent.closest(key);
            }
        }
        return ret;
    }

    public SfPanel remove() {
        SfPanel prev = this.prev;
        SfPanel next = this.next;
        prev.next = next;
        next.prev = prev;
        return this;
    }

    public ArrayList<SfPanel> getChildren() {
        ArrayList<SfPanel> children = new ArrayList<SfPanel>();
        SfPanel child = this.firstChild;
        while (child != null) {
            children.add(child);
            child = child.next;
        }
        return children;
    }

    public SfPanel setZIndex(int zIndex) {
        this.zIndex = zIndex;
        return this;
    }

    public SfPanel setVisible(boolean visible) {
        this.visible = visible;
        return this;
    }

    public SfPanel calcSize(Context context) {
        float parentW = 0;
        float parentH = 0;
        if (this.visible) {
            DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
            if (this.parent != null) {
                parentW = this.parent.frame.width - (this.position == SF_POSITION_ABSOLUTE ? this.parent.padding.getWidth() : 0);
                parentH = this.parent.frame.height - (this.position == SF_POSITION_ABSOLUTE ? this.parent.padding.getHeight() : 0);
            } else {
                parentW = metrics.widthPixels;
                parentH = metrics.heightPixels;
            }
            this.frame.width = this.size.width >= 0 ? this.size.width : (parentW * -this.size.width ) / 100;
            this.frame.height = this.size.height >= 0 ? this.size.height : (parentH * -this.size.height ) / 100;
            switch (this.position) {
                case SF_POSITION_RELATIVE:
                    // Do nothing YAY!
                    break;
                case SF_POSITION_ABSOLUTE:
                    if (this.origin.left != SF_UNSET && this.origin.right != SF_UNSET) {
                        this.frame.width = parentW - (this.origin.left + this.origin.right);
                    }
                    if (this.origin.top != SF_UNSET && this.origin.bottom != SF_UNSET) {
                        this.frame.height = parentH - (this.origin.top + this.origin.bottom);
                    }
                    break;
                case SF_POSITION_FIXED:
                    if (this.origin.left != SF_UNSET && this.origin.right != SF_UNSET) {
                        this.frame.width = metrics.widthPixels - (this.origin.left + this.origin.right);
                    }
                    if (this.origin.top != SF_UNSET && this.origin.bottom != SF_UNSET) {
                        ActionBar actionbar = ((ActionBarActivity)context).getSupportActionBar();
                        boolean fullScreen = (((ActionBarActivity)context).getWindow().getAttributes().flags & WindowManager.LayoutParams.FLAG_FULLSCREEN) != 0;
                        boolean actionbarVisible = actionbar != null ? actionbar.isShowing() : false;
                        int offset = 0;
                        if (!fullScreen) {
                            int resourceId = Resources.getSystem().getIdentifier("status_bar_height", "dimen", "android");
                            offset += resourceId > 0 ? Resources.getSystem().getDimensionPixelSize(resourceId) : 0;
                        }
                        if (actionbarVisible) {
                            TypedValue tv = new TypedValue();
                            context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true);
                            offset += context.getResources().getDimensionPixelSize(tv.resourceId) + 12;
                        }
                        this.frame.height = metrics.heightPixels - (offset + this.origin.top + this.origin.bottom);
                    }
                    break;
            }
            // Size children panels
            SfPanel child = this.firstChild;
            while (child != null) {
                child.calcSize(context);
                child = child.next;
            }
        }
        return this;
    }

    public SfPanel calcPos(Context context) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        if (this.parent == null) {
            // Root panel
            this.frame.x = this.origin.left + this.margin.left;
            this.frame.y = this.origin.top + this.margin.top;
        }
        // Position children
        float srcX = 0;
        float srcY = 0;
        float srcW = this.frame.width;
        float srcH = this.frame.height;
        switch (this.alignment) {
            case SF_ALIGNMENT_LEFT:
            case SF_ALIGNMENT_CENTER:
                srcX = (this.scrollHost ? 0 : this.frame.x) + this.padding.left;
                srcY = (this.scrollHost ? 0 : this.frame.y) + this.padding.top;
                break;
            case SF_ALIGNMENT_RIGHT:
                srcX = this.frame.width - this.padding.left;
                srcY = (this.scrollHost ? 0 : this.frame.y) + this.padding.top;
                break;
        }
        float curX = srcX;
        float curY = srcY;
        boolean newLine = true;
        float lineHeight = 0;
        int line = 0;
        // Iterate through children panels
        SfPanel panel = this.firstChild;
        while ( panel != null ) {
            float panelW = panel.frame.width + panel.margin.getWidth();
            float panelH = panel.frame.height + panel.margin.getHeight();
            if (! panel.visible ) continue;
            switch (panel.position) {
                case SF_POSITION_RELATIVE:
                    // Check horizontal alignment
                    switch (this.alignment) {
                        case SF_ALIGNMENT_LEFT:
                            if ( srcW - curX < panelW ) {
                                // Is the panel bigger?
                                curX = srcX;
                                curY += lineHeight;
                                lineHeight = 0;
                                line++;
                            }
                            lineHeight = lineHeight < panelH ? panelH : lineHeight;
                            panel.line = line;
                            panel.frame.x = curX + panel.margin.left;
                            panel.frame.y = curY + panel.margin.top;
                            // Offset the current X
                            curX += panelW;
                            break;
                        case SF_ALIGNMENT_RIGHT:
                            if ( curX - panelW <= 0 ) {
                                // Is the panel bigger?
                                curX = srcX;
                                curY += lineHeight;
                                lineHeight = 0;
                                line++;
                            }
                            lineHeight = lineHeight < panelH ? panelH : lineHeight;
                            panel.line = line;
                            panel.frame.x = curX - (panel.frame.width + panel.margin.left);
                            panel.frame.y = curY + panel.margin.top;
                            // Offset the current X
                            curX -= panelW;
                            break;
                        case SF_ALIGNMENT_CENTER:
                            if ( panelW > srcW - curX ) {
                                // Is the panel bigger?
                                curX = srcX;
                                curY += lineHeight;
                                lineHeight = 0;
                                line++;
                            }
                            lineHeight = lineHeight < panelH ? panelH : lineHeight;
                            panel.line = line;
                            panel.frame.x = curX + panel.margin.left;
                            panel.frame.y = curY + panel.margin.top;
                            // Offset the current X
                            curX += panelW;
                    }
                break;
                case SF_POSITION_ABSOLUTE:
                    //if (panel.origin.left == SF_UNSET || panel.origin.right == SF_UNSET) {
                        // In this case, it is relative to the parent
                        float parentRight = this.frame.x + this.frame.width - this.margin.right;
                        panel.frame.x = (panel.origin.left != SF_UNSET) ? (this.frame.x + panel.origin.left + panel.margin.left) : (parentRight - (panelW + panel.origin.right));
                        //}
                    //if (panel.origin.top == SF_UNSET || panel.origin.bottom == SF_UNSET) {
                        // In this case, it is relative to the parent
                        float parentBottom = this.frame.y + this.frame.height - this.margin.bottom;
                        panel.frame.y = (panel.origin.top != SF_UNSET) ? (this.frame.y + panel.origin.top + panel.margin.top) : (parentBottom - (panelH + panel.origin.bottom));
                    //}
                break;
                case SF_POSITION_FIXED:
                    //if (panel.origin.left == SF_UNSET || panel.origin.right == SF_UNSET) {
                        // This time is relative TO the screen size
                        panel.frame.x = (panel.origin.left != SF_UNSET) ? (panel.origin.left + panel.margin.left) : (metrics.widthPixels - (panelW + panel.origin.right));
                    //}
                    //if (panel.origin.top == SF_UNSET || panel.origin.bottom == SF_UNSET) {
                        // This time is relative TO the screen size
                        ActionBar actionbar = ((ActionBarActivity)context).getSupportActionBar();
                        boolean fullScreen = (((ActionBarActivity)context).getWindow().getAttributes().flags & WindowManager.LayoutParams.FLAG_FULLSCREEN) != 0;
                        boolean actionbarVisible = actionbar != null ? actionbar.isShowing() : false;
                        int offset = 0;
                        if (!fullScreen) {
                            int resourceId = Resources.getSystem().getIdentifier("status_bar_height", "dimen", "android");
                            offset += resourceId > 0 ? Resources.getSystem().getDimensionPixelSize(resourceId) : 0;
                        }
                        if (actionbarVisible) {
                            TypedValue tv = new TypedValue();
                            context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true);
                            offset += context.getResources().getDimensionPixelSize(tv.resourceId) + 12;
                        }
                        panel.frame.y = (panel.origin.top != SF_UNSET) ? (panel.origin.top + panel.margin.top) : (metrics.heightPixels - (offset + panelH + panel.origin.bottom));
                    //}
                break;
            }
            // Layout its children too
            panel.calcPos(context);
            // Increment iterator
            panel = panel.next;
        }
        // Second pass to center children
        if (this.alignment == SF_ALIGNMENT_CENTER) {
            float[] lineWidths = new float[line + 1];
            float[] lineHeights = new float[line + 1];
            panel = this.firstChild;
            // Measure
            while (panel != null) {
                if (panel.position == SF_POSITION_RELATIVE) {
                    float panelW = panel.frame.width + panel.margin.getWidth();
                    float panelH = panel.frame.height + panel.margin.getHeight();
                    // Add to the line width
                    lineWidths[panel.line] += panelW;
                    lineHeights[panel.line] = lineHeights[panel.line] >= panelH ? lineHeights[panel.line] : panelH;
                }
                // Increment iterator
                panel = panel.next;
            }
            // Iterate the lines
            this.scrollHeight = 0;
            for (int i = 0; i < lineWidths.length; i++) {
                lineWidths[i] = (srcW / 2) - (lineWidths[i] / 2);
                this.scrollHeight += lineHeights[i];
            }
            // Now move the panels
            panel = this.firstChild;
            while ( panel != null ) {
                if (panel.position == SF_POSITION_RELATIVE) {
                    float panelW = panel.frame.width + panel.margin.getWidth();
                    // Get the line width we're working on
                    panel.frame.x = lineWidths[panel.line];
                    lineWidths[panel.line] += panelW;
                }
                // Next panel
                panel = panel.next;
            }
        }
        if (this.size.height == 0 && this.position == SF_POSITION_RELATIVE) {
            this.frame.height = this.scrollHeight;
        }
        return this;
    }

    public SfPanel update(Context context) {
        this.calcSize(context);
        this.calcPos(context);
        //
        AbsoluteLayout.LayoutParams params = new AbsoluteLayout.LayoutParams( (int)this.frame.width, (int)this.frame.height, (int)this.frame.x, (int)this.frame.y );
        if (this.view != null) {
            this.view.setLayoutParams(params);
        }
        // Size children panels
        SfPanel child = this.firstChild;
        while (child != null) {
            child.update(context);
            child = child.next;
        }
        return this;
    }

}