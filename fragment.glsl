#version 330 core

out vec4 finalcolor;


in vec3 interpolatedNormal;
in vec2 st;

uniform mat4 T;

void main() {

    //finalcolor = vec4(interpolatedNormal, 1.0);

    // vec3 L is the light direction
    // vec3 V is the view direction - (0,0,1) in view space
    // vec3 N is the normal
    // vec3 R is the computed reflection direction
    // float n is the ``shininess'' parameter
    // vec3 ka is the ambient reflection color
    // vec3 Ia is the ambient illumination color
    // vec3 kd is the diffuse surface reflection color
    // vec3 Id is the diffuse illumination color
    // vec3 ks is the specular surface reflection color
    // vec3 Is is the specular illumination color
    
    vec3 L = normalize(mat3(T) * vec3(0.0f, 0.1f, 1.0f));

    vec3 V = vec3(0.0, 0.0, 1.0);
    //vec3 V = normalize(vec3(-0.4f,-0.4f,-1.0f));

    vec3 N = interpolatedNormal;
    float n = 5;
    vec3 Ia = vec3(0.25, 0.1, 0.0);
    vec3 ka = vec3(0.25, 0.1, 0.0);
    vec3 Id = vec3(1.0, 0.5, 0.0);
    vec3 kd = vec3(1.0, 0.5, 0.0);
    vec3 Is = vec3(1.0, 1.0, 1.0);
    vec3 ks = vec3(1.0, 1.0, 1.0);


    vec3 R = 2.0 * dot(N, L) * N - L;
    float dotNL = max(dot(N, L), 0.0);
    float dotRV = max(dot(R, V), 0.0);
    if (dotNL == 0.0) {
	    dotRV = 0.0;  // Do not show highlight on the dark side
    }
    vec3 shadedcolor = Ia * ka + Id * kd * dotNL + Is * ks * pow(dotRV, n);
    finalcolor = vec4(shadedcolor, 1.0);


}
