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

import com.occultusterra.raylib.structs.Vector2;

public class V2 {
	
	public V2(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public void Set(V2 v) {
		this.x = v.x;
		this.y = v.y;
	}
	
	public void Set(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	/* Basic Math */
	public V2 add(V2 v) {
		return new V2(v.x + this.x, v.y + this.y);
	}
	
	public V2 add(float f) {
		return new V2(this.x + f, this.y + f);
	}
	
	public V2 sub(V2 v) {
		return new V2(this.x - v.x, this.y - v.y);
	}
	
	public V2 sub(float f) {
		return new V2(this.x - f, this.y - f);
	}
	
	public V2 mul(V2 v) {
		return new V2(v.x * this.x, v.y * this.y);
	}
	
	public V2 mul(float f) {
		return new V2(this.x * f, this.y * f);
	}
	
	public V2 div(V2 v) {
		return new V2(this.x / v.x, this.y / v.y);
	}
	
	public V2 div(float f) {
		return new V2(this.x / f, this.y / f);
	}
	
	/* Useful Functions */
	public boolean Equals(V2 v) {
		return this.x == v.x && this.y == v.y;
	}
	
	public float Length() {
		return (float) Math.sqrt( Math.pow(this.x, this.x) + Math.pow(this.y, this.y)); 
	}
	
	public float Distance(V2 v) { 
		return (float) Math.sqrt(Math.pow(this.x - v.x, 2) + Math.pow(this.y - v.y, 2));
	}
	
	public float Dot(V2 v) {
		return (this.x * v.x) + (this.y * v.y);
	}
	
	public V2 Normalized() {
		float length = Length();
		return new V2(this.x / length, this.y / length);
	}
	
	public Vector2.ByValue getStruct() {
		return new Vector2.ByValue(this.x, this.y);
	}
	
	@Override
	public String toString() {
		return new String("V2["+this.x+","+this.y+"]");
	}
	
	public float x;
	public float y;
}
