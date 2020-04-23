#include <jni.h>
#include <stdio.h>

JNIEXPORT void JNICALL
Java_j_HelloWorld_print(JNIEnv *env, jobject obj)
{
printf("Hello World!\n");
return;
}