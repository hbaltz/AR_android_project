/**
 * Copyright 2013 Dennis Ippel
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.example.hbaltz.aton.rajawali.rajawali3d.animation;

import org.rajawali3d.animation.*;

public interface IAnimationListener {

	public void onAnimationEnd(org.rajawali3d.animation.Animation animation);

	public void onAnimationRepeat(org.rajawali3d.animation.Animation animation);

	public void onAnimationStart(org.rajawali3d.animation.Animation animation);

	public void onAnimationUpdate(org.rajawali3d.animation.Animation animation, double interpolatedTime);
}
