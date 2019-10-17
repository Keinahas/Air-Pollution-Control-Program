#ifdef __unix__                    /* __unix__ is usually defined by compilers targeting Unix systems */

    #define OS_Windows 0
    #include <stdio.h>
    #include <stdlib.h>
    #include <string.h>

#elif defined(_WIN32) || defined(WIN32)     /* _Win32 is usually defined by compilers targeting 32 or   64 bit Windows systems */

    #define OS_Windows 1
    #include <stdio.h>
    #include <stdlib.h>
    #include <string.h>

#endif

int main(int argc, char **argv){
    
    FILE *fp = NULL;
    char line[100];
    char str[] = "javac -d ../class ";
    system("find . | grep .java > FileLists.txt");

    fp = fopen("FileLists.txt", "r");
    if(fp == NULL) exit(EXIT_FAILURE);

    while(fscanf(fp,"%s", line) == 1){
        char * cmd = (char *)malloc(strlen(str) + strlen(line) + 1);
        strcpy(cmd, str);
        strcat(cmd, line);
        system(cmd);
        printf("%s\n", cmd);
        free(cmd);
    }
    fclose(fp);
    remove("FileLists.txt");

    char * cmd = (char *)malloc(strlen("cd ../class && java ") + strlen(argv[1]) + 1);
    strcpy(cmd, "cd ../class && java ");
    strcat(cmd, argv[1]);
    system(cmd);

    return 0;
}