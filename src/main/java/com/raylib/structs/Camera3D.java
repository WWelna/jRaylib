/*
* Copyright (c) 2021 William Welna (wwelna@occultusterra.com)
*
* This software is provided "as-is", without any express or implied warranty. In no event 
* will the authors be held liable for any damages arising from the use of this software.
*
* Permission is granted to anyone to use this software for any purpose, including commercial 
* applications, and to alter it and redistribute it freely, subject to the following restrictions:
*
*  1. The origin of this software must not be misrepresented; you must not claim that you 
*  wrote the original software. If you use this software in a product, an acknowledgment 
*  in the product documentation would be appreciated but is not required.
*
*  2. Altered source versions must be plainly marked as such, and must not be misrepresented
*  as being the original software.
*
*  3. This notice may not be removed or altered from any source distribution. 
*/

package com.occultusterra.raylib.structs;

import com.sun.jna.Structure;
import com.sun.jna.Pointer;

@Structure.FieldOrder({"position", "target", "up", "fovy", "projection"})
public class Camera3D extends Structure {
	
	public Camera3D() { super(); }
	public Camera3D(Pointer peer) { super(peer); read(); }
	
	public static class ByReference extends Camera3D implements Structure.ByReference {
		public ByReference() { super(); }
		public ByReference(Pointer memory) { super(memory); }
	}

	public static class ByValue extends Camera3D implements Structure.ByValue {
		public ByValue() { super(); }
		public ByValue(Pointer memory) { super(memory); }
		public Camera3D.ByReference getReference() { return new Camera3D.ByReference(this.getPointer()); }
		
		public ByValue(Vector3.ByValue position, Vector3.ByValue target, Vector3.ByValue up, float fovy, int projection) {
			super();
			this.position = position;
			this.target = target;
			this.up = up;
			this.fovy = fovy;
			this.projection = projection;
		}
	}
	
	public Vector3.ByValue position;
	public Vector3.ByValue target;
	public Vector3.ByValue up;
	public float fovy;
	public int projection;
}
