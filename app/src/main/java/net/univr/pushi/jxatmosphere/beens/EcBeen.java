package net.univr.pushi.jxatmosphere.beens;


import java.util.List;

public class EcBeen {

    /**
     * data : {"time":["003","003","006","006","006","006","009","009","012","012","012","012","012","012","015","015","018","018","018","018","021","021","024","024","024","024","024","024","024","024","027","027","030","030","030","030","033","033","036","036","036","036","036","036","036","036","039","039","042","042","042","042","045","045","048","048","048","048","048","048","048","048","051","051","054","054","054","054","057","057","060","060","060","060","060","060","060","060","063","063","066","066","066","066","069","069","072","072","072","072","072","072","072","072","078","078","084","084","084","084","084","084","090","090","096","096","096","096","096","096","102","102","108","108","108","108","108","108","114","114","120","120","120","120","120","120","126","126","132","132","132","132","132","132","138","138","144","144","144","144","144","144","150","150","156","156","156","156","156","156","162","162","168","168","168","168","168","168","174","174","180","180","180","180","180","180","186","186","192","192","192","192","192","192","198","198","204","204","204","204","204","204","210","210","216","216","216","216","216","216","222","222","228","228","228","228","228","228","234","234","240","240","240","240","240","240"],"url":["http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain03/2018050800_003.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt03/2018050800_003.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt03/2018050800_006.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain03/2018050800_006.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt06/2018050800_006.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain06/2018050800_006.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain03/2018050800_009.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt03/2018050800_009.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt03/2018050800_012.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain03/2018050800_012.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt06/2018050800_012.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain12/2018050800_012.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt12/2018050800_012.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain06/2018050800_012.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain03/2018050800_015.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt03/2018050800_015.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain06/2018050800_018.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain03/2018050800_018.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt06/2018050800_018.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt03/2018050800_018.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt03/2018050800_021.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain03/2018050800_021.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain03/2018050800_024.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain06/2018050800_024.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain24/2018050800_024.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain12/2018050800_024.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt03/2018050800_024.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt06/2018050800_024.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt12/2018050800_024.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt24/2018050800_024.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt03/2018050800_027.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain03/2018050800_027.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain03/2018050800_030.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain06/2018050800_030.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt03/2018050800_030.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt06/2018050800_030.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt03/2018050800_033.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain03/2018050800_033.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain03/2018050800_036.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain06/2018050800_036.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain12/2018050800_036.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain24/2018050800_036.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt03/2018050800_036.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt06/2018050800_036.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt12/2018050800_036.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt24/2018050800_036.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt03/2018050800_039.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain03/2018050800_039.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain03/2018050800_042.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain06/2018050800_042.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt03/2018050800_042.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt06/2018050800_042.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt03/2018050800_045.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain03/2018050800_045.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain03/2018050800_048.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain06/2018050800_048.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain24/2018050800_048.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain12/2018050800_048.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt03/2018050800_048.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt12/2018050800_048.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt24/2018050800_048.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt06/2018050800_048.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt03/2018050800_051.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain03/2018050800_051.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain03/2018050800_054.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain06/2018050800_054.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt03/2018050800_054.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt06/2018050800_054.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt03/2018050800_057.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain03/2018050800_057.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain03/2018050800_060.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain06/2018050800_060.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain12/2018050800_060.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain24/2018050800_060.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt24/2018050800_060.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt12/2018050800_060.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt03/2018050800_060.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt06/2018050800_060.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt03/2018050800_063.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain03/2018050800_063.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain06/2018050800_066.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt03/2018050800_066.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain03/2018050800_066.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt06/2018050800_066.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain03/2018050800_069.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt03/2018050800_069.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt03/2018050800_072.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain03/2018050800_072.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt06/2018050800_072.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain06/2018050800_072.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain24/2018050800_072.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain12/2018050800_072.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt12/2018050800_072.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt24/2018050800_072.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain06/2018050800_078.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt06/2018050800_078.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt06/2018050800_084.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain06/2018050800_084.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain12/2018050800_084.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain24/2018050800_084.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt24/2018050800_084.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt12/2018050800_084.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain06/2018050800_090.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt06/2018050800_090.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt06/2018050800_096.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain06/2018050800_096.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain24/2018050800_096.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain12/2018050800_096.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt12/2018050800_096.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt24/2018050800_096.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain06/2018050800_102.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt06/2018050800_102.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt06/2018050800_108.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain06/2018050800_108.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain12/2018050800_108.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain24/2018050800_108.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt24/2018050800_108.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt12/2018050800_108.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt06/2018050800_114.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain06/2018050800_114.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain06/2018050800_120.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain24/2018050800_120.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt06/2018050800_120.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt24/2018050800_120.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt12/2018050800_120.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain12/2018050800_120.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt06/2018050800_126.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain06/2018050800_126.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain06/2018050800_132.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain24/2018050800_132.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt06/2018050800_132.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt24/2018050800_132.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt12/2018050800_132.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain12/2018050800_132.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt06/2018050800_138.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain06/2018050800_138.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain06/2018050800_144.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain24/2018050800_144.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt06/2018050800_144.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt24/2018050800_144.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt12/2018050800_144.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain12/2018050800_144.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt06/2018050800_150.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain06/2018050800_150.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain06/2018050800_156.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain24/2018050800_156.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt06/2018050800_156.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt12/2018050800_156.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt24/2018050800_156.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain12/2018050800_156.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt06/2018050800_162.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain06/2018050800_162.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain06/2018050800_168.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain24/2018050800_168.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt06/2018050800_168.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt12/2018050800_168.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain12/2018050800_168.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt24/2018050800_168.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt06/2018050800_174.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain06/2018050800_174.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain06/2018050800_180.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain24/2018050800_180.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt06/2018050800_180.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt12/2018050800_180.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt24/2018050800_180.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain12/2018050800_180.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt06/2018050800_186.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain06/2018050800_186.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain06/2018050800_192.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain24/2018050800_192.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt06/2018050800_192.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt12/2018050800_192.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain12/2018050800_192.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt24/2018050800_192.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt06/2018050800_198.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain06/2018050800_198.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain06/2018050800_204.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain24/2018050800_204.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt06/2018050800_204.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt12/2018050800_204.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt24/2018050800_204.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain12/2018050800_204.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt06/2018050800_210.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain06/2018050800_210.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain06/2018050800_216.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain24/2018050800_216.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt06/2018050800_216.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt12/2018050800_216.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain12/2018050800_216.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt24/2018050800_216.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt06/2018050800_222.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain06/2018050800_222.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain06/2018050800_228.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain24/2018050800_228.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt06/2018050800_228.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt12/2018050800_228.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt24/2018050800_228.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain12/2018050800_228.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt06/2018050800_234.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain06/2018050800_234.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain06/2018050800_240.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain24/2018050800_240.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt06/2018050800_240.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt12/2018050800_240.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/rain12/2018050800_240.png","http://stwx.jxgis.cn/models/ecmwf_thin/2018050800/bp/rain/xt24/2018050800_240.png"]}
     * errmsg : success
     * errcode : 0
     */

    private DataBean data;
    private String errmsg;
    private String errcode;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public static class DataBean {
        private List<String> time;
        private List<String> url;

        public List<String> getTime() {
            return time;
        }

        public void setTime(List<String> time) {
            this.time = time;
        }

        public List<String> getUrl() {
            return url;
        }

        public void setUrl(List<String> url) {
            this.url = url;
        }
    }
}
