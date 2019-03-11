
rem netsh interface ip set address name="本地连接" source= static addr= 10.110.110.110 mask= 255.255.255.0


rem netsh int ipv6 add route ::/0 "本地连接" metric=1

@echo off
echo "请输入profile......"

set /p uName=请输入profile:
echo "您输入的是:"
echo %uName%
:: 设置列15宽度 行1长度
::@echo off&&mode con cols=15 lines=1  
@if "%uName%"=="branch" (echo "测试服务器启动......"
	echo "if结束符测试"
)
@if "%uName%"=="trunk" (
echo "拷贝trunk程序包到桌面......"
copy /y D:\workspace\trunk\cupid\cupid-web\target\cupid.war C:\Users\Administrator\Desktop\cupid.war
copy /y D:\workspace\trunk\zeus-server\zeus-server-web\target\zeus-server-web.war C:\Users\Administrator\Desktop\zeus-server-web.war
echo "拷贝trunk程序包到桌面......完成"
echo "请查看文件更新时间:"
dir C:\Users\Administrator\Desktop\ |find "zeus-server-web.war"
dir C:\Users\Administrator\Desktop\ |find "cupid.war"
)
pause
