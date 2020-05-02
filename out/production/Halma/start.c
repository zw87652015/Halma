#include <stdlib.h>
#include <windows.h>

int main()
{
    HWND hwnd;
    hwnd = FindWindow("ConsoleWindowClass", NULL);
    if (hwnd)
    {
        ShowOwnedPopups(hwnd, SW_HIDE);
        ShowWindow(hwnd, SW_HIDE);
    }
    system("java xyz.chengzi.halma.Halma");
    //getch();
}