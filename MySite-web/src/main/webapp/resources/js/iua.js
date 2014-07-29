/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



iS = '<img src="' + (window.location.protocol == 'https:' ? 'https' : 'http') +
        '://r.i.ua/s?u181512&p62&n' + Math.random();
iD = document;
if (!iD.cookie)
    iD.cookie = "b=b; path=/";
if (iD.cookie)
    iS += '&c1';
iS += '&d' + (screen.colorDepth ? screen.colorDepth : screen.pixelDepth)
        + "&w" + screen.width + '&h' + screen.height;
iT = iR = iD.referrer.replace(iP = /^[a-z]*:\/\//, '');
iH = window.location.href.replace(iP, '');
((iI = iT.indexOf('/')) != -1) ? (iT = iT.substring(0, iI)) : (iI = iT.length);
if (iT != iH.substring(0, iI))
    iS += '&f' + escape(iR);
iS += '&r' + escape(iH);
iD.write(iS + '" border="0" width="88" height="31" />');
