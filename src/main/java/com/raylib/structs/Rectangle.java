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

@Structure.FieldOrder({"x", "y", "width", "height"})
public class Rectangle extends Structure {
	
	public Rectangle() { super(); }
	public Rectangle(Pointer peer) { super(peer); read(); }
	
	public static class ByReference extends Rectangle implements Structure.ByReference {
		public ByReference() { super(); }
		public ByReference(Pointer memory) { super(memory); }
	}

	public static class ByValue extends Rectangle implements Structure.ByValue {
		public ByValue() { super(); }
		public ByValue(Pointer memory) { super(memory); }
		public Rectangle.ByReference getReference() { return new Rectangle.ByReference(this.getPointer()); }
		
		public ByValue(float x, float y, float width, float height) {
			super();
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
		}
	}
	
	public float x;
	public float y;
	public float width;
	public float height;
}
