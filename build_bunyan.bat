@ECHO off
ECHO -------------------------------- Building Bunyan -----------------------------------
ECHO Backing up src
XCOPY src src-bak /E /I /Q
ECHO.
ECHO Copying client source 
XCOPY bunyan\src\minecraft src\minecraft /E /Q
XCOPY bunyan\src\common src\minecraft /E /Q
ECHO.
ECHO Copying server sources
XCOPY bunyan\src\minecraft_server src\minecraft_server /E /Q
XCOPY bunyan\src\common src\minecraft_server /E /Q
ECHO.
CALL recompile.bat
CALL reobfuscate.bat
ECHO.
ECHO Adding release assets
XCOPY /Q MMPL-1.0.txt reobf\minecraft\
XCOPY /Q MMPL-1.0.txt reobf\minecraft_server\
XCOPY /Q bunyan\LICENSE.txt reobf\minecraft\
XCOPY /Q bunyan\LICENSE.txt reobf\minecraft_server\
XCOPY /Q bunyan\mcmod.info reobf\minecraft\
XCOPY /Q bunyan\mcmod.info reobf\minecraft_server\
XCOPY /Q bunyan\src\common\bunyan\blocks\blocks.png reobf\minecraft\bunyan\blocks\
XCOPY /Q bunyan\src\common\bunyan\items\items.png reobf\minecraft\bunyan\items\
XCOPY /Q bunyan\artwork\logo.png reobf\minecraft\bunyan\
XCOPY /q bunyan\artwork\logo.png reobf\minecraft_server\bunyan\
ECHO.
ECHO Restoring src-bak
RMDIR /S /Q src
REN src-bak src
PAUSE
