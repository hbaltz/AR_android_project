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
package com.example.hbaltz.aton.rajawali.rajawali3d.postprocessing;

import java.util.List;

import org.rajawali3d.postprocessing.*;
import org.rajawali3d.renderer.Renderer;


public interface IPostProcessingEffect extends IPostProcessingComponent {
	void initialize(Renderer renderer);
	void removePass(org.rajawali3d.postprocessing.IPass pass);
	void removeAllPasses();
	org.rajawali3d.postprocessing.IPass addPass(org.rajawali3d.postprocessing.IPass pass);
	List<org.rajawali3d.postprocessing.IPass> getPasses();
	void setRenderToScreen(boolean renderToScreen);
}
