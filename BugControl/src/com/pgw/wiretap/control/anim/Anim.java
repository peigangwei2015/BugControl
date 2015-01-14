package com.pgw.wiretap.control.anim;


import com.pgw.wiretap.control.R;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;

public class Anim {
	public static void scaleAnim(final Context context,final View v,final long s){
				
				AnimationSet animationSet=new AnimationSet(true);
				Animation animation = AnimationUtils.loadAnimation(context, R.anim.scale);
				animationSet.addAnimation(animation);
				animationSet.setDuration(s);
				animationSet.setFillAfter(true);
				v.startAnimation(animationSet);
				try {
					Thread.sleep(s);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				v.setVisibility(View.GONE);
				
	}
}
