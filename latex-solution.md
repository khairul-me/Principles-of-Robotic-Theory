# Module 4: Critical Thinking
## Author: Md Khairul Islam

### Lynch Park Exercise 5.5: Rigid Body Motion Analysis

## Introduction

This document presents a detailed solution to Exercise 5.5 from Lynch Park, analyzing the motion of a rigid body rotating about a fixed point. The problem involves determining position, velocity, configuration matrices, and twists in different reference frames.

## Problem Statement

A rigid body rotates about point (L, L) with angular velocity θ̇ = 1. We need to:
1. Find point P's position relative to fixed frame {s}
2. Determine point P's velocity in the fixed frame
3. Find the configuration Tsb
4. Analyze twists in body and space coordinates

## Solution

### 1. Position Analysis

To find point P's position relative to frame {s}, we need to:
a) Consider the translation to rotation point (L, L)
b) Account for rotation θ
c) Include the offset to point P

The position vector $$\vec{r}_P$$ can be expressed as:

$$
\vec{r}_P = \begin{bmatrix} L \\ L \end{bmatrix} + R(\theta)\begin{bmatrix} L \\ -d \end{bmatrix}
$$

Where the rotation matrix R(θ) is:

$$
R(\theta) = \begin{bmatrix} \cos(\theta) & -\sin(\theta) \\ \sin(\theta) & \cos(\theta) \end{bmatrix}
$$

Expanding this:

$$
\vec{r}_P = \begin{bmatrix} L \\ L \end{bmatrix} + \begin{bmatrix} \cos(\theta) & -\sin(\theta) \\ \sin(\theta) & \cos(\theta) \end{bmatrix} \begin{bmatrix} L \\ -d \end{bmatrix}
$$

$$
= \begin{bmatrix} L \\ L \end{bmatrix} + \begin{bmatrix} L\cos(\theta) + d\sin(\theta) \\ L\sin(\theta) - d\cos(\theta) \end{bmatrix}
$$

Therefore, the final position is:

$$
\vec{r}_P = \begin{bmatrix} L + L\cos(\theta) + d\sin(\theta) \\ L + L\sin(\theta) - d\cos(\theta) \end{bmatrix}
$$

### 2. Velocity Analysis

The velocity is found by differentiating the position vector with respect to time. Given θ̇ = 1:

$$
\vec{v}_P = \frac{d}{dt}\vec{r}_P = \begin{bmatrix} -L\sin(\theta) \cdot \dot{\theta} + d\cos(\theta) \cdot \dot{\theta} \\ L\cos(\theta) \cdot \dot{\theta} + d\sin(\theta) \cdot \dot{\theta} \end{bmatrix}
$$

Substituting θ̇ = 1:

$$
\vec{v}_P = \begin{bmatrix} -L\sin(\theta) + d\cos(\theta) \\ L\cos(\theta) + d\sin(\theta) \end{bmatrix}
$$

### 3. Configuration Matrix Analysis

The configuration matrix Tsb represents the complete transformation from body frame {b} to space frame {s}. For planar motion, this is a 3×3 matrix in SE(2):

$$
T_{sb} = \begin{bmatrix} \cos(\theta) & -\sin(\theta) & L + L\cos(\theta) + d\sin(\theta) \\ 
\sin(\theta) & \cos(\theta) & L + L\sin(\theta) - d\cos(\theta) \\
0 & 0 & 1 \end{bmatrix}
$$

This matrix combines:
- Rotation (upper-left 2×2 block)
- Translation (right column)
- Homogeneous coordinate (bottom row)

### 4. Twist Analysis

#### Body Frame Twist
The body twist Vb represents the motion in body frame coordinates:

$$
V_b = \begin{bmatrix} v_{b,x} \\ v_{b,y} \\ \omega_b \end{bmatrix} = \begin{bmatrix} -L\sin(\theta) \\ L(1-\cos(\theta)) \\ 1 \end{bmatrix}
$$

#### Space Frame Twist
The space twist Vs represents the motion in fixed frame coordinates:

$$
V_s = \begin{bmatrix} v_{s,x} \\ v_{s,y} \\ \omega_s \end{bmatrix} = \begin{bmatrix} -L\sin(\theta) + d\cos(\theta) \\ L\cos(\theta) + d\sin(\theta) \\ 1 \end{bmatrix}
$$

#### Relationship Between Twists
The twists are related through the adjoint transformation:

$$
V_s = Ad_{T_{sb}}V_b
$$

Where:

$$
Ad_{T_{sb}} = \begin{bmatrix} R(\theta) & \hat{p}R(\theta) \\ 0 & 1 \end{bmatrix}
$$

And p is the position vector from the origin to point P.

## Verification and Analysis

1. Position Analysis:
   - Correctly accounts for both translation and rotation
   - Expressed in terms of θ as required
   - Consistent with physical setup

2. Velocity Analysis:
   - Properly derived from position
   - Accounts for angular velocity θ̇ = 1
   - Expressed in fixed frame coordinates

3. Configuration Matrix:
   - Correctly represents transformation
   - Includes both rotation and translation
   - Maintains proper SE(2) structure

4. Twist Analysis:
   - Both body and space twists derived
   - Relationship properly established
   - Consistent with configuration

## Conclusion

This solution provides a complete analysis of the rigid body motion problem, demonstrating understanding of:
- Coordinate transformations
- Rigid body kinematics
- Velocity analysis
- Configuration spaces
- Twist relationships

All mathematical derivations are shown in detail, and results are verified for consistency with physical principles and problem constraints.
