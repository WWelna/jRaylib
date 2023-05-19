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
import com.sun.jna.ptr.FloatByReference;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.ShortByReference;

@Structure.FieldOrder({"vertexCount", "triangleCount", "vertices", "texcoords", "texcoords2", "normals", "tangents", "colors", "indices", "animVertices", "animNormals", "boneIds", "boneWeights", "vaoId", "vboId"})
public class Mesh extends Structure {
	
	public Mesh() { super(); }
	public Mesh(Pointer peer) { super(peer); read(); }
	
	public static class ByReference extends Mesh implements Structure.ByReference {
		public ByReference() { super(); }
		public ByReference(Pointer memory) { super(memory); }
	}

	public static class ByValue extends Mesh implements Structure.ByValue {
		public ByValue() { super(); }
		public ByValue(Pointer memory) { super(memory); }
		public Mesh.ByReference getReference() { return new Mesh.ByReference(this.getPointer()); }
	}
	
	public int vertexCount;
	public int triangleCount;

	// Most of these are Arrays of pointers, but in practice shouldn't need direct accessed... hopefully...
	public FloatByReference vertices;
	public FloatByReference texcoords;
	public FloatByReference texcoords2;
	public FloatByReference normals;
	public FloatByReference tangents;
	public Pointer /* unsigned char * */ colors;
	public ShortByReference /* unsigned short * */ indices;

	public FloatByReference animVertices;
	public FloatByReference animNormals;
	public Pointer /* unsigned char * */ boneIds;
	public FloatByReference boneWeights;

	public int vaoId;
	public IntByReference vboId;
}
