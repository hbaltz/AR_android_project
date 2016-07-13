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
package com.example.hbaltz.aton.rajawali.rajawali3d.materials.textures;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.rajawali3d.materials.AResourceManager;
import org.rajawali3d.materials.textures.*;
import org.rajawali3d.materials.textures.ATexture.TextureException;
import org.rajawali3d.renderer.Renderer;

import android.opengl.GLES20;

/**
 * A singleton class that keeps track of all textures used by the application. All textures will be restored when the
 * OpenGL context is being recreated. This however needs to be indicated by setting
 * {@link org.rajawali3d.materials.textures.ATexture#shouldRecycle(boolean)} to true (which is the default). It will then keep a reference to the Bitmap
 * which means the application will take up more memory.
 * <p>
 * The advantage of storing the Bitmap in memory is that it the texture can quickly be recovered when the context is
 * restored.
 *
 * @author dennis.ippel
 *
 */
public final class TextureManager extends AResourceManager {
	/**
	 * Stores the singleton instance
	 */
	private static TextureManager instance = null;
	/**
	 * A list of managed textures
	 */
	private List<org.rajawali3d.materials.textures.ATexture> mTextureList;

	/**
	 * The constructor can only be instantiated by the TextureManager class itself.
	 */
	private TextureManager()
	{
		mTextureList = Collections.synchronizedList(new CopyOnWriteArrayList<org.rajawali3d.materials.textures.ATexture>());
		mRenderers = Collections.synchronizedList(new CopyOnWriteArrayList<Renderer>());
	}

	/**
	 *
	 * @return The TextureManager instance
	 */
	public static TextureManager getInstance()
	{
		if (instance == null)
		{
			instance = new TextureManager();
		}
		return instance;
	}

	/**
	 * Adds a new {@link org.rajawali3d.materials.textures.ATexture} to the TextureManager. If a texture by the same name already exists that is not
	 * this same texture object, the provided texture will be updated to point to the previously added texture.
	 *
	 * @param texture
	 * @return
	 */
	public org.rajawali3d.materials.textures.ATexture addTexture(org.rajawali3d.materials.textures.ATexture texture) {
		mRenderer.addTexture(texture);
		return texture;
	}

	/**
	 * Adds a {@link org.rajawali3d.materials.textures.ATexture} to the TextureManager. This should only be called by {@link Renderer}.
	 *
	 * @param texture
	 */
	public void taskAdd(org.rajawali3d.materials.textures.ATexture texture) {
		taskAdd(texture, false);
	}

	/**
	 * Adds a {@link org.rajawali3d.materials.textures.ATexture} to the TextureManager. This should only be called by {@link Renderer}.
	 *
	 * @param texture
	 * @param isUpdatingAfterContextWasLost
	 */
	private void taskAdd(org.rajawali3d.materials.textures.ATexture texture, boolean isUpdatingAfterContextWasLost) {
		if (!isUpdatingAfterContextWasLost) {
			// -- check if texture exists already
			int count = mTextureList.size();
			for (int i = 0; i < count; i++) {
				if (mTextureList.get(i).getTextureName().equals(texture.getTextureName())) {
					if (mTextureList.get(i) != texture)
						texture.setFrom(mTextureList.get(i));
					else
						return;
				}
			}
			texture.setOwnerIdentity(mRenderer.getClass().toString());
		}

		try {
			texture.add();
		} catch (TextureException e) {
			throw new RuntimeException(e);
		}

		if (!isUpdatingAfterContextWasLost)
			mTextureList.add(texture);
	}

	/**
	 * Replaces an existing {@link org.rajawali3d.materials.textures.ATexture}.
	 *
	 * @param texture
	 * @return
	 */
	public void replaceTexture(org.rajawali3d.materials.textures.ATexture texture) {
		mRenderer.replaceTexture(texture);
	}

	/**
	 * Replaces an existing {@link org.rajawali3d.materials.textures.ATexture}. This should only be called by {@link Renderer}.
	 *
	 * @param texture
	 * @return
	 */
	public void taskReplace(org.rajawali3d.materials.textures.ATexture texture)
	{
		try {
			texture.replace();
		} catch (TextureException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Removes a {@link org.rajawali3d.materials.textures.ATexture} from the TextureManager.
	 *
	 * @param texture
	 * @return
	 */
	public void removeTexture(org.rajawali3d.materials.textures.ATexture texture) {
		mRenderer.removeTexture(texture);
	}

	/**
	 * Removes a list of {@link org.rajawali3d.materials.textures.ATexture}s from the TextureManager.
	 *
	 * @param texture
	 * @return
	 */
	public void removeTextures(List<org.rajawali3d.materials.textures.ATexture> textures) {
		int numTextures = textures.size();

		for (int i = 0; i < numTextures; i++) {
			removeTexture(textures.get(i));
		}
	}

	/**
	 * Removes a {@link org.rajawali3d.materials.textures.ATexture} from the TextureManager. This should only be called by {@link Renderer}.
	 *
	 * @param texture
	 * @return
	 */
	public void taskRemove(org.rajawali3d.materials.textures.ATexture texture) {
		try {
			texture.remove();
		} catch (TextureException e) {
			throw new RuntimeException(e);
		}
		mTextureList.remove(texture);
	}

	/**
	 * Restores all textures that are managed by the TextureManager. All textures will be restored when the OpenGL
	 * context is being recreated. This however needs to be indicated by setting {@link org.rajawali3d.materials.textures.ATexture#shouldRecycle(boolean)}
	 * to true (which is the default). It will then keep a reference to the Bitmap which means the application will take
	 * up more memory.
	 * <p>
	 * The advantage of storing the Bitmap in memory is that it the texture can quickly be recovered when the context is
	 * restored.
	 */
	public void reload() {
		mRenderer.reloadTextures();
	}

	/**
	 * Restores all textures that are managed by the TextureManager. This should only be called by
	 * {@link Renderer}.
	 */
	public void taskReload() {
		int len = mTextureList.size();
		for (int i = 0; i < len; i++) {
			org.rajawali3d.materials.textures.ATexture texture = mTextureList.get(i);
			if (texture.willRecycle()) {
				mTextureList.remove(i);
				i -= 1;
				len -= 1;
			} else {
				taskAdd(texture, true);
			}
		}
	}

	/**
	 * Completely resets the TextureManager. Disposes the Bitmaps and removes all references.
	 */
	public void reset() {
		mRenderer.resetTextures();
	}

	/**
	 * Completely resets the TextureManager. This should only be called by {@link Renderer}.
	 */
	public void taskReset() {
		try {
			int count = mTextureList.size();

			int[] textures = new int[count];
			for (int i = 0; i < count; i++) {
				org.rajawali3d.materials.textures.ATexture texture = mTextureList.get(i);
				if (texture.getOwnerIdentity().equals(mRenderer.getClass().toString()) || texture.willRecycle()) {
					texture.reset();
					textures[i] = texture.getTextureId();
					mTextureList.remove(i);
					i -= 1;
					count -= 1;
				}
			}

			if(Renderer.hasGLContext())
				GLES20.glDeleteTextures(count, textures, 0);

			if (mRenderers.size() > 0) {
				mRenderer = mRenderers.get(mRenderers.size() - 1);
				reload();
			} else {
				mTextureList.clear();
			}
		} catch (TextureException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Completely resets the TextureManager. This should only be called by {@link Renderer}.
	 *
	 * @param renderer
	 */
	public void taskReset(Renderer renderer) {
		if (mRenderers.size() == 0) {
			taskReset();
		}
	}

	public void taskResizeRenderTarget(RenderTargetTexture renderTargetTexture) {
		renderTargetTexture.resize();
	}

	/**
	 * Returns the number of textures currently managed.
	 *
	 * @return
	 */
	public int getTextureCount() {
		return mTextureList.size();
	}
}
