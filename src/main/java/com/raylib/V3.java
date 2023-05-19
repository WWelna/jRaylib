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

package com.occultusterra.raylib;

import com.occultusterra.raylib.structs.Vector3;

public class V3 {
	
	public V3(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public void Set(Vector3 v) {
		this.x = v.x;
		this.y = v.y;
		this.z = v.z;
	}
	
	public void Set(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/* Basic Math */
	public V3 add(V3 v) {
		return new V3(v.x + this.x, v.y + this.y, this.z + v.z);
	}
	
	public V3 add(float f) {
		return new V3(this.x + f, this.y + f, this.z + f);
	}
	
	public V3 sub(Vector3 v) {
		return new V3(this.x - v.x , this.y - v.y, this.z - v.z);
	}
	
	public V3 sub(float f) {
		return new V3(this.x - f, this.y - f, this.z - f);
	}
	
	public V3 mul(V3 v) {
		return new V3(v.x * this.x, v.y * this.y, v.z * this.z);
	}
	
	public V3 mul(float f) {
		return new V3(this.x * f, this.y * f, this.z * f);
	}
	
	public V3 div(V3 v) {
		return new V3(this.x / v.x, this.y / v.y, this.z / v.z);
	}
	
	public V3 div(float f) {
		return new V3(this.x / f, this.y / f, this.z / f);
	}
	
	/* Useful Functions */
	public boolean Equals(V3 v) {
		return this.x == v.x && this.y == v.y && this.z == v.z;
	}
	
	public float Length() {
		return (float)Math.sqrt(Math.pow(this.x, this.x) + Math.pow(this.y, this.y) + Math.pow(this.z, this.z));
	}
	
	public float Distance(V3 v) { 
		return (float)Math.sqrt(Math.pow(this.x - v.x, 2) +  Math.pow(this.y - v.y, 2) + Math.pow(this.z - v.z, 2));
	}
	
	public float Dot(V3 v) {
		return (this.x * v.x) + (this.y * v.y) + (this.z * v.z);
	}
	
	public V3 Normalized() {
		float length = Length();
		return new V3(this.x / length, this.y / length, this.z / length);
	}
	
	public Vector3.ByValue getStruct() {
		return new Vector3.ByValue(this.x, this.y, this.z);
	}
	
	@Override
	public String toString() {
		return new String("V3["+this.x+","+this.y+","+this.z+"]");
	}
	
	public float x;
	public float y;
	public float z;
}
