package com.librarysystem.services;

import javax.servlet.http.HttpServletRequest;

public interface IpAddressProvider {
    String getRequestIpAddress(HttpServletRequest httpRequest);
}
