package com.vt;

import javax.xml.bind.DatatypeConverter;

import static java.lang.Integer.parseInt;
import static java.lang.Integer.toHexString;

public class StringToHex {
  public static void main(String[] args) {

    //System.out.println(toHexString(parseInt("10001000", 2)));
    System.out.println(new String(DatatypeConverter.parseHexBinary("313030453030303036384152427562584D30616B746C6556394252564E6652554E4341414141414141414141414141414141414141416769575A6230512B74337A503046684F316B414E72413D3D30303030303030353330303031353630333039313935303335333039343730383438373832303330395039353033353333393635383338343932393037202020333834393030303020202035383132373036323031373738343932393030353831323030303947454E4552414C204D45524348414E54202020202020202020554E4B4E4F574E202020202043484E303030303030303030303030303030303030303030303030303030303030303030303035303031303834302020203530303130383430202020303131463033303820202020202020303031353631313030313030202020303030303032202020202020303532302020303030303030303533303030313536363130303030303030303030303030303739353038343032303030303031354330303030303030303233393135363030303030303030414D454D443030303030303030303030202020202020202020202020202020202020202030202020202020383845363043433832373639333438392020203030303630453046304338303030303030383030303932464537303930434138483739303230363541303130334130323030322020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020202020303034423343303032313033303931353620202020202020202020202020202020202020202020202020202020202020202020202020202020202020203030303030303030303533303030313536383030303030303030303030303035453033303032324130303030303030323530313038303120202020202020202020202020202020303030313030303030303030202020202020202020202020202020202020202020202020202020202020")));
  }

}
