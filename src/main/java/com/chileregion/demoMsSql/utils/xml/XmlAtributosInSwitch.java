package com.chileregion.demoMsSql.utils.xml;

public class XmlAtributosInSwitch {
    public String enumEntradaInSwitch(XmlAtributos.AtributosEntrada atributosEntrada){
        switch (atributosEntrada){
            case SIGNATURE_XMLS : return "http://www.w3.org/2000/09/xmldsig#";
            case CANON_ALGOR : return "http://www.w3.org/TR/2001/REC-xml-c14n-20010315";
            case SIGNMETH_ALGOR : return "http://www.w3.org/2000/09/xmldsig#rsa-sha1";
            case REFERENCE_URI: return "";
            case TRANSFORM_ALGOR : return "http://www.w3.org/2000/09/xmldsig#enveloped-signature";
            case DIGMETH_ALGOR : return "http://www.w3.org/2000/09/xmldsig#sha1";
            default:
                return "";
        }
    }

    public String enumValoresInSwitch(XmlAtributos.ValoresEntrada valoresEntrada){
        switch (valoresEntrada){
            case DIGVAL: return "8slcL05kmrM8NGw4I9NSfRqYA9E=";
            case SIGNATURE_VALUE: return "jlbzatIIBLW8AjH++5uVTTrGIMVwGButuoAR88y/hvSc1+6/eW1K864fK3cKi76oArqk7lAM4pP" +
                    "okoXme0JT/hRXXGo6ecuKzO18z2WfPWwgnN0f3ac03TDu7PwfqiDG9mhQpYfIkNp6GNJIiqlg9PG2w1fOJ1QoypsrQmKq6YU=";
            case MODULULS: return ">2Pb4kEB19m7NmOUYew9f36325yrTLTPMU7qzYG2A0/BsubxDdgQw2Op0x6zXvOVX" +
                    "sYI9KkPXtD5orKJMjwxYRv9wUWdyiE776Rv4ljfJO7EQhIK1fDQDnPt0HefBS06Xzg2QLBvLR+pe1vc6C02Dr99v+lnLA8" +
                    "mnZiJlRHndhNU=";
            case EXPONENT: return "AQAB";
            case X509CERT: return ">MIIF1DCCBLygAwIBAgIDAQNtMA0GCSqGSIb3DQEBBQUAMIHGMQswCQYDVQQG\n" +
                    "EwJDTDEYMBYGA1UEChMPQWNlcHRhLmNvbSBTLkEuMTgwNgYDVQQLEy9BdXRv\n" +
                    "cmlkYWQgY2VydGlmaWNhZG9yYSBDbGFzZSAzIHBlcnNvbmEgbmF0dXJhbDFD\n" +
                    "MEEGA1UEAxM6QWNlcHRhLmNvbSBBdXRvcmlkYWQgY2VydGlmaWNhZG9yYSBD\n" +
                    "bGFzZSAzIHBlcnNvbmEgbmF0dXJhbDEeMBwGCSqGSIb3DQEJARYPaW5mb0Bh\n" +
                    "Y2VwdGEuY29tMB4XDTAxMDkyNTIxMDgxMloXDTAyMDkyNTIxMDgxMlowgZ8x\n" +
                    "CzAJBgNVBAYTAkNMMRgwFgYDVQQKEw9BY2VwdGEuY29tIFMuQS4xLDAqBgNV\n" +
                    "BAsTI0NlcnRpZmljYWRvIENsYXNlIDMgUGVyc29uYSBOYXR1cmFsMRwwGgYJ\n" +
                    "KoZIhvcNAQkBFg1uY2hlbGVAc2lpLmNsMSowKAYDVQQDEyFOSUNPTEFTIFpB\n" +
                    "UFJJQU4gQ0hFTEVCSUZTS0kgQkFFWkEwgZ8wDQYJKoZIhvcNAQEBBQADgY0A\n" +
                    "MIGJAoGBANj2+JBAdfZuzZjlGHsPX9+t9ucq0y0zzFO6s2BtgNPwbLm8Q3YE\n" +
                    "MNjqdMes17zlV7GCPSpD17Q+aKyiTI8MWEb/cFFncohO++kb+JY3yTuxEISC\n" +
                    "tXw0A5z7dB3nwUtOl84NkCwby0fqXtb3OgtNg6/fb/pZywPJp2YiZUR53YTV\n" +
                    "AgMBAAGjggJyMIICbjAdBggrBgEEAbVrDwQRFg9BY2VwdGEuY29tIFMuQS4w\n" +
                    "JQYDVR0RBB4wHKAaBggrBgEEAcEBAaAOFgwxMC40MTEuODcxLTIwDwYIKwYB\n" +
                    "Jh0z1DR3Pl3xOiaFIjSXsQO2PSzcA3wZXYF+KDrMul8e5lAF2NNiLmMVtXEx\n" +
                    "ZykMaTGGWS0ZETDhJmBwEZGpP4+lt/JhgwF1Sb6wdrXp7MFCJUc1Tj+/5JqH\n" +
                    "1kP0E63/hVElrcP0g8Zn8Z+vr/PMGW1kKgE0IyS4iJ8eIhNSK5phFyKJUn0l\n" +
                    "BmIZX7u89d5u7X8=";
            default :
                return "";
        }
    }
}
