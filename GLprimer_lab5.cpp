/*
 * A C++ framework for OpenGL programming in TNM046 for MT1
 *
 * This is a small, limited framework, designed to be easy to use for students in an introductory
 * computer graphics course in the first year of a M Sc curriculum. It uses custom code for some
 * things that are better solved by external libraries like GLM, but the emphasis is on simplicity
 * and readability, not generality.
 *
 * For the window management, GLFW 3.x is used for convenience.
 * The framework should work in Windows, MacOS X and Linux.
 * GLEW is used for handling platform specific OpenGL extensions.
 * This code depends only on GLFW, GLEW, and OpenGL libraries.
 * OpenGL 3.3 or higher is required.
 *
 * Authors: Stefan Gustavson (stegu@itn.liu.se) 2013-2015
 *          Martin Falk (martin.falk@liu.se) 2021
 *
 * This code is in the public domain.
 */
#if defined(WIN32) && !defined(_USE_MATH_DEFINES)
#define _USE_MATH_DEFINES
#endif

// File and console I/O for logging and error reporting
#include <iostream>
// Math header for trigonometric functions
#include <cmath>

// glew provides easy access to advanced OpenGL functions and extensions
#include <GL/glew.h>

// GLFW 3.x, to handle the OpenGL window
#include <GLFW/glfw3.h>

#include "Utilities.hpp"
#include <vector>
#include "Shader.hpp"
#include <array>
#include <cstdio>

#include "TriangleSoup.hpp"
#include "Texture.hpp"
#include "Rotator.hpp"


GLuint createVertexBuffer(int location, int dimensions, const std::vector<float>& vertices) {
    GLuint bufferID;
    // Generate buffer, activate it and copy the data
    glGenBuffers(1, &bufferID);
    glBindBuffer(GL_ARRAY_BUFFER, bufferID);
    glBufferData(GL_ARRAY_BUFFER, vertices.size() * sizeof(float), vertices.data(), GL_STATIC_DRAW);
    // Tell OpenGL how the data is stored in our buffer
    // Attribute location (must match layout(location=#) statement in shader)
    // Number of dimensions (3 -> vec3 in the shader, 2-> vec2 in the shader),
    // type GL_FLOAT, not normalized, stride 0, start at element 0
    glVertexAttribPointer(location, dimensions, GL_FLOAT, GL_FALSE, 0, nullptr);
    // Enable the attribute in the currently bound VAO
    glEnableVertexAttribArray(location);

    return bufferID;
}

GLuint createIndexBuffer(const std::vector<unsigned int>& indices) {
    GLuint bufferID;
    // Generate buffer, activate it and copy the data
    glGenBuffers(1, &bufferID);
    // Activate (bind) the index buffer and copy data to it.
    glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, bufferID);
    // Present our vertex indices to OpenGL
    glBufferData(GL_ELEMENT_ARRAY_BUFFER, indices.size() * sizeof(unsigned int), indices.data(),
                 GL_STATIC_DRAW);

    return bufferID;
}


 void mat4print(const std::array<float, 16>& m) {
    printf("Matrix:\n");
    printf("%6.2f %6.2f %6.2f %6.2f\n", m[0], m[4], m[8], m[12]);
    printf("%6.2f %6.2f %6.2f %6.2f\n", m[1], m[5], m[9], m[13]);
    printf("%6.2f %6.2f %6.2f %6.2f\n", m[2], m[6], m[10], m[14]);
    printf("%6.2f %6.2f %6.2f %6.2f\n", m[3], m[7], m[11], m[15]);
    printf("\n");
}

std::array<float, 16> mat4mult(const std::array<float, 16>& m1, const std::array<float, 16>& m2) {
    std::array<float, 16> results = {0};

    for (int i = 0; i < 4; i++) {
        for (int j = 0; j < 4; j++) {
            for (int k = 0; k < 4; k++) {
                results[4 * i + j] += m1[4 * k + j] * m2[4 * i + k];
            }
        }
    }

    return results;
}

std::array<float, 16> mat4identity() {
    return {
        1.0f, 0.0f, 0.0f, 0.0f, 
        0.0f, 1.0f, 0.0f, 0.0f,
        0.0f, 0.0f, 1.0f, 0.0f, 
        0.0f, 0.0f, 0.0f, 1.0f};
}

std::array<float, 16> mat4rotx(float angle) {
    return {
        1.0f, 0.0f, 0.0f, 0.0f, 
        0.0f, cos(angle), sin(angle), 0.0f,
        0.0f, -sin(angle), cos(angle), 0.0f, 
        0.0f, 0.0f, 0.0f, 1.0f};
}

std::array<float, 16> mat4roty(float angle) {
    return {
        cosf(angle), 0.0f, -sinf(angle), 0.0f, 
        0.0f, 1.0f, 0.0f, 0.0f,
        sinf(angle), 0.0f, cosf(angle),  0.0f, 
        0.0f, 0.0f, 0.0f, 1.0f};
}

std::array<float, 16> mat4rotz(float angle) {
    return {
        cosf(angle), sinf(angle), 0.0f, 0.0f, 
        -sinf(angle), cosf(angle), 0.0f, 0.0f,
        0.0f, 0.0f, 1.0f, 0.0f, 
        0.0f, 0.0f, 0.0f, 1.0f};
}

std::array<float, 16> mat4scale(float scale) {
    return {
        scale, 0.0f, 0.0f,  0.0f, 
        0.0f, scale, 0.0f, 0.0f,
        0.0f,  0.0f, scale, 0.0f, 
        0.0f, 0.0f,  0.0f, 1.0f};
}

std::array<float, 16> mat4translate(float x, float y, float z) {
    return {
        1.0f, 0.0f, 0.0f, 0.0f, 
        0.0f, 1.0f, 0.0f, 0.0f, 
        0.0f, 0.0f, 1.0f, 0.0f, 
        x, y, z, 1.0f};
}

std::array<float, 16> mat4perspective(float vfov, float aspect, float znear, float zfar) {
    float f = cos(vfov/2) / sin(vfov/2);
    return {
        f/aspect, 0.0f, 0.0f, 0.0f,
        0.0f, f, 0.0f, 0.0f,
        0.0f, 0.0f, -(zfar + znear) / (zfar - znear), -1.0f,
        0.0f, 0.0f, -(2 * znear * zfar) / (zfar / znear), 0.0f
    };
}




int main(int, char*[]) {

    std::array<GLfloat, 16> matT = {
        1.0f, 0.0f, 0.0f, 0.0f, 
        0.0f, 1.0f, 0.0f, 0.0f,
        0.0f, 0.0f, 1.0f, 0.0f, 
        0.0f, 0.0f, 0.0f, 1.0f
    };

    std::array<GLfloat, 16> matT1 = {
        1.0f, 0.0f, 0.0f, 0.0f, 
        0.0f, 1.0f, 0.0f, 0.0f,
        0.0f, 0.0f, 1.0f, 0.0f, 
        0.0f, 0.0f, 0.0f, 1.0f
    };

    std::array<GLfloat, 16> matT2 = {
        1.0f, 0.0f, 0.0f, 0.0f, 
        0.0f, 1.0f, 0.0f, 0.0f,
        0.0f, 0.0f, 1.0f, 0.0f, 
        0.0f, 0.0f, 0.0f, 1.0f
    };

    std::array<GLfloat, 16> MV = {
        1.0f, 0.0f, 0.0f, 0.0f, 
        0.0f, 1.0f, 0.0f, 0.0f,
        0.0f, 0.0f, 1.0f, 0.0f, 
        0.0f, 0.0f, 0.0f, 1.0f
    };

    std::array<GLfloat, 16> P = {
        1.0f, 0.0f, 0.0f, 0.0f, 
        0.0f, 1.0f, 0.0f, 0.0f,
        0.0f, 0.0f, 1.0f, 0.0f, 
        0.0f, 0.0f, 0.0f, 1.0f
    };

    Shader myShader;


    // Initialise GLFW
    glfwInit();

    const GLFWvidmode* vidmode;  // GLFW struct to hold information about the display
    // Determine the desktop size
    vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

    // Make sure we are getting a GL context of at least version 3.3
    glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
    glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
    // Enable the OpenGL core profile
    glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
    glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE);

    // Open a square window (aspect 1:1) to fill half the screen height
    GLFWwindow* window =
        glfwCreateWindow(vidmode->height / 2, vidmode->height / 2, "GLprimer", nullptr, nullptr);
    if (!window) {
        std::cout << "Unable to open window. Terminating.\n";
        glfwTerminate();  // No window was opened, so we can't continue in any useful way
        return -1;
    }

    // Make the newly created window the "current context" for OpenGL
    // (This step is strictly required or things will simply not work)
    glfwMakeContextCurrent(window);

    // Initialize glew
    GLenum err = glewInit();
    if (GLEW_OK != err) {
        std::cerr << "Error: " << glewGetErrorString(err) << "\n";
        glfwTerminate();
        return -1;
    }

    myShader.createShader("C:\\Skola\\TNM046 - Datorgrafik\\Lab\\Debug\\vertex.glsl", "C:\\Skola\\TNM046 - Datorgrafik\\Lab\\Debug\\fragment.glsl");
    


    // Show some useful information on the GL context
    std::cout << "GL vendor:       " << glGetString(GL_VENDOR)
              << "\nGL renderer:     " << glGetString(GL_RENDERER)
              << "\nGL version:      " << glGetString(GL_VERSION)
              << "\nDesktop size:    " << vidmode->width << " x " << vidmode->height << "\n";

    // Get window size. It may start out different from the requested size and
    // will change if the user resizes the window
    int width, height;


    glfwSwapInterval(0);  // Do not wait for screen refresh between frames

    GLint locationTime = glGetUniformLocation(myShader.id(), "time");
    if (locationTime == -1) {  // If the variable is not found, -1 is returned
        std::cout << "Unable to locate variable 'time' in shader!\n";
    }


    TriangleSoup earth;
    earth.createSphere(0.3, 50);

    GLint earthTex = glGetUniformLocation(myShader.id(), "tex");
    Texture earthTexture;
    earthTexture.createTexture("textures/earth.tga");



    TriangleSoup myShape;
    myShape.readOBJ("meshes/trex.obj");

    GLint locationTex = glGetUniformLocation(myShader.id(), "tex");
    Texture myTexture;
    myTexture.createTexture("textures/trex.tga");




    KeyRotator myKeyRotator(window);
    MouseRotator myMouseRotator(window);




    glEnable(GL_DEPTH_TEST);
    glEnable(GL_CULL_FACE);

    glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);

    // Main loop
    while (!glfwWindowShouldClose(window)) {

        // Set the clear color to a dark gray (RGBA)
        glClearColor(0.3f, 0.3f, 0.3f, 0.0f);

        glfwGetWindowSize(window, &width, &height);
        // Set viewport. This is the pixel rectangle we want to draw into
        glViewport(0, 0, width, height);  // The entire window

        // Clear the color and depth buffers for drawing
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);



        // ---- Rendering code should go here ---- 
        float time = static_cast<float>(glfwGetTime());
        glUniform1f(locationTime, time);                 // Copy the value to the shader program



        // --- Put this in the rendering loop
        myKeyRotator.poll();
        // Create a rotation matrix that depends on myKeyRotator.phi and myKeyRotator.theta
        matT1 = mat4mult(mat4roty(myKeyRotator.phi()), mat4rotx(myKeyRotator.theta()));

        myMouseRotator.poll();
        // Create rotation matrix that depends on myMouseRotator.phi and myMouseRotator.theta
        matT2 = mat4mult(mat4roty(myMouseRotator.phi()), mat4rotx(myMouseRotator.theta()));


        MV = mat4translate(0, 0, -3);
        MV = mat4mult(MV, mat4rotx(M_PI/8));
        MV = mat4mult(MV, mat4roty(M_PI_4));

        matT1 = mat4mult(matT1, MV);
        matT2 = mat4mult(matT2, MV);


        P = mat4perspective(M_PI_4, 1, 0.1, 100.0);

        MV = matT1;

        GLint locationMV = glGetUniformLocation(myShader.id(), "MV");
        glUseProgram(myShader.id());  // Activate the shader to set its variables
        glUniformMatrix4fv(locationMV, 1, GL_FALSE, MV.data());  // Copy the value

        GLint locationP = glGetUniformLocation(myShader.id(), "P");
        glUseProgram(myShader.id());  // Activate the shader to set its variables
        glUniformMatrix4fv(locationP, 1, GL_FALSE, P.data());  // Copy the value

        
        GLint locationT = glGetUniformLocation(myShader.id(), "T");
        glUseProgram(myShader.id());  // Activate the shader to set its variables
        glUniformMatrix4fv(locationT, 1, GL_FALSE, matT.data());  // Copy the value
        
        util::displayFPS(window);



        glBindTexture(GL_TEXTURE_2D, myTexture.id());

        glUseProgram(myShader.id());
        glUniform1i(locationTex, 0);
        myShape.render();

        
        MV = matT2;

        GLint locationMV2 = glGetUniformLocation(myShader.id(), "MV");
        glUseProgram(myShader.id());  // Activate the shader to set its variables
        glUniformMatrix4fv(locationMV2, 1, GL_FALSE, MV.data());  // Copy the value



        glBindTexture(GL_TEXTURE_2D, earthTexture.id());

        glUseProgram(myShader.id());
        glUniform1i(earthTex, 0);
        earth.render();






        // restore previous state (no texture, no shader)
        glBindTexture(GL_TEXTURE_2D, 0);
        glUseProgram(0);





        // Swap buffers, display the image and prepare for next frame
        glfwSwapBuffers(window);

        // Poll events (read keyboard and mouse input)
        glfwPollEvents();

        // Exit if the ESC key is pressed (and also if the window is closed)
        if (glfwGetKey(window, GLFW_KEY_ESCAPE)) {
            glfwSetWindowShouldClose(window, GL_TRUE);
        }
    }

    // Close the OpenGL window and terminate GLFW
    glfwDestroyWindow(window);
    glfwTerminate();
}
