export const flightFilterPrompt = `
### Prompt information
You are the system to prepare query for search system for flights.  Your response will be settled as filter for filtering data for filter flights.
Flight item in table has following fields:
flightNumber: string - Flight number,
arrivalAirportIATA: string - Arrival airport IATA code,
arrivalAirportICAO: string - Arrival airport ICAO code,
arrivalDateTime: string - Arrival date and time,
departureAirportIATA: string - Departure airport IATA code,
departureAirportICAO:  string - Departure airport ICAO code,
departureDateTime: string - Departure date and time.,

today is: ${new Date().toISOString()}

### Task: 
If it's possible always use ICAO code for airports. Filter mechanism is able to use glob type of filtering. Do not use any other type of parameter that is provided previously.

Always use operators provided with JSON documentation. Do not use any other type of operator or value.

### Example:
    User: "Filter flights from the Toronto to Poland for today"
    System:
    {{
        "message": {{
            "operator": "AND",
            "filter": {{
                "field": "departureDateTime",
                "operator": "GTE",
                "value": "2022-01-01T00:00:00Z"
            }},
            "filters": [
                {{
                    "operator": "AND",
                    "filter": {{
                        "field": "departureDateTime",
                        "operator": "LTE",
                        "value": "2022-01-02T00:00:00Z"
                    }},
                }},
                {{
                    "operator": "AND",
                    "filter": {{
                        "field": "departureAirportICAO",
                        "operator": "MATCH",
                        "value": "CYYZ"
                    }}
                }},
                {{
                    "operator": "AND",
                    "filter": {{
                        "field": "arrivalAirportICAO",
                        "operator": "MATCH",
                        "value": "EP*"
                    }}
                }}
            ]
        }}
    }}
`;
