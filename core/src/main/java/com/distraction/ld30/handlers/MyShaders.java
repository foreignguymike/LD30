package com.distraction.ld30.handlers;

import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.distraction.ld30.LD30;

public abstract class MyShaders {
	
	private MyShaders() {}
	
	public static final String VERT_INVERT = "attribute vec4 " + ShaderProgram.POSITION_ATTRIBUTE + ";\n"
			+ "attribute vec4 " + ShaderProgram.COLOR_ATTRIBUTE + ";\n"
			+ "attribute vec2 " + ShaderProgram.TEXCOORD_ATTRIBUTE + "0;\n"
			+ "uniform mat4 u_projTrans;\n"
			+ "varying vec4 v_color;\n"
			+ "varying vec2 v_texCoords;\n"
			+ "\n"
			+ "void main()\n"
			+ "{\n"
			+ "   v_color = " + ShaderProgram.COLOR_ATTRIBUTE + ";\n"
			+ "   v_color.a = v_color.a * (256.0/255.0);\n"
			+ "   v_texCoords = " + ShaderProgram.TEXCOORD_ATTRIBUTE + "0;\n"
			+ "   gl_Position =  u_projTrans * " + ShaderProgram.POSITION_ATTRIBUTE + ";\n"
			+ "}\n";
	public static final String FRAG_INVERT = "#ifdef GL_ES\n"
			+ "#define LOWP lowp\n"
			+ "precision mediump float;\n"
			+ "#else\n"
			+ "#define LOWP \n"
			+ "#endif\n"
			+ "varying LOWP vec4 v_color;\n"
			+ "varying vec2 v_texCoords;\n"
			+ "uniform sampler2D u_texture;\n"
			+ "void main()\n"
			+ "{\n"
			+ "  vec4 color = v_color * texture2D(u_texture, v_texCoords);\n"
			+ "  if(gl_FragCoord.y < " + LD30.HEIGHT + ".0 * " + LD30.SCALE + ".0 / 2.0) {\n"
			+ "    vec3 invert = vec3(1.0 - color.rgb);\n"
			+ "    gl_FragColor = vec4(invert, color.a);\n"
			+ "  }\n"
			+ "  else {\n"
			+ "    gl_FragColor = color;\n"
			+ "  }\n"
			+ "}";
	
}
