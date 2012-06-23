@echo off
echo == Prepping Bunyan ==

echo ^> Copying client assets
xcopy /q MMPL-1.0.txt reobf\minecraft\
xcopy /q textfiles\* reobf\minecraft\
xcopy /q src\minecraft\bunyan\blocks\blocks.png reobf\minecraft\bunyan\blocks\
xcopy /q artwork\logo.png reobf\minecraft\bunyan\

echo ^> Copying server assets
xcopy /q MMPL-1.0.txt reobf\minecraft_server\
xcopy /q textfiles\* reobf\minecraft_server\
xcopy /q artwork\logo.png reobf\minecraft_server\bunyan\
pause