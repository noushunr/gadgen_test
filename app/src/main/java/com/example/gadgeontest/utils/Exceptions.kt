package com.example.gadgeontest.utils

import java.io.IOException


/**
 * Created by Noushad N on 06-06-2022.
 */

class ApiException(message: String) : IOException(message)
class NoInternetException(message: String) : IOException(message)
class ErrorBodyException(errorResponse: String?) : IOException(errorResponse)