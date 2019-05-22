package com.mailang.ddtask.strategy;

public enum PolicyType
{
    DATE("date"),
    REPEAT("repeat"),
    TAGS("tags");
    private String type;
    private PolicyType(String type)
    {
        this.type = type;
    }

    public static PolicyType getPolicy(String type)
    {
        for (PolicyType policyType : PolicyType.values())
        {
            if (policyType.type.equals(type))
            {
                return policyType;
            }
        }
        return DATE;
    }
}
